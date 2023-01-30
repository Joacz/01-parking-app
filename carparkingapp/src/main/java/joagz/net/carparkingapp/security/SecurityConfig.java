package joagz.net.carparkingapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import javax.sql.DataSource;
import joagz.net.carparkingapp.jwt.utils.Jwks;
import joagz.net.carparkingapp.user.utils.UserModelDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private RSAKey rsaKey;

  @Bean
  UserDetailsService userModelDetailsService() {
    return new UserModelDetailsService();
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Bean
  // public UserDetailsManager users(DataSource dataSource) {
  //   UserDetails user = User
  //     .withUsername("admin")
  //     .password(passwordEncoder().encode("1234"))
  //     .roles("ADMIN")
  //     .build();
  //   JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
  //   users.setInsertGroupAuthoritySql(
  //     "insert into userAuthority(user, authority) values (?,?)"
  //   );
  //   users.createUser(user);
  //   return users;
  // }

  @Bean
  public JwtAuthenticationConverter jwtAuthenticationConverter() {
    final JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    grantedAuthoritiesConverter.setAuthorityPrefix("");

    final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
      grantedAuthoritiesConverter
    );
    return jwtAuthenticationConverter;
  }

  @Order(Ordered.HIGHEST_PRECEDENCE)
  @Bean
  SecurityFilterChain tokenSecurityFilterChain(HttpSecurity http)
    throws Exception {
    return http
      .securityMatcher("/token")
      .authorizeHttpRequests(auth -> auth.requestMatchers("/token").permitAll())
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .httpBasic(withDefaults())
      .build();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(auth ->
        auth
          .requestMatchers("/api/v1/lots/**", "/api/v1/spots/**")
          .hasAnyAuthority("ADMIN", "USER")
          .requestMatchers("/api/v1/users/**")
          .hasAuthority("ADMIN")
          .anyRequest()
          .authenticated()
      )
      .oauth2ResourceServer(oauth2 -> oauth2.jwt())
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
      )
      .build();
  }

  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    rsaKey = Jwks.generateRsa();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public JwtDecoder jwtDecoder() throws JOSEException {
    return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
  }
}

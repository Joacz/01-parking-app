package joagz.net.carparkingapp.user.utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import joagz.net.carparkingapp.db.UsersJpa;
import joagz.net.carparkingapp.models.UserModel;
import joagz.net.carparkingapp.repo.UsersRepository;
import joagz.net.carparkingapp.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserModelDetailsService implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    Optional<Users> userByUsername = usersRepository.findByUsername(username);

    if (!userByUsername.isPresent()) {
      throw new UsernameNotFoundException("Invalid credentials");
    }
    Users user = userByUsername.get();
    if (user == null || !user.getUsername().equals(username)) {
      throw new UsernameNotFoundException("Invalid credentials");
    }

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    user
      .getAuthorities()
      .stream()
      .forEach(a ->
        grantedAuthorities.add(new SimpleGrantedAuthority((a.getAuthority())))
      );

    return new UserModel(
      user.getUsername(),
      user.getPassword(),
      true,
      true,
      true,
      true,
      grantedAuthorities,
      user.getCreation_date(),
      user.getEmail(),
      user.getFirstName(),
      user.getLastName()
    );
  }
}

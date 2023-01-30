package joagz.net.carparkingapp.models;

import java.util.Collection;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
@ToString
public class UserModel extends User {

  private static final long serialVersionUID = 1L;

  public UserModel(
    String username,
    String password,
    boolean enabled,
    boolean accountNonExpired,
    boolean credentialsNonExpired,
    boolean accountNonLocked,
    Collection<? extends GrantedAuthority> authorities,
    Date creation_date,
    String email,
    String firstName,
    String lastName
  ) {
    super(
      username,
      password,
      enabled,
      accountNonExpired,
      credentialsNonExpired,
      accountNonLocked,
      authorities
    );
    this.creation_date = creation_date;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private String email;
  private String firstName;
  private String lastName;
  private Date creation_date;
}

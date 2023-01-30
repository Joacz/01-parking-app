package joagz.net.carparkingapp.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;
import joagz.net.carparkingapp.models.Authorities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;

  private String username;
  private String password;

  @Builder.Default
  private Boolean accountNonExpired = true;

  @Builder.Default
  private Boolean accountNonLocked = true;

  @Builder.Default
  private Boolean enabled = true;

  @Builder.Default
  private Boolean credentialsNonExpired = true;

  @Singular
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
    name = "userAuthority",
    joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(
      name = "authority",
      referencedColumnName = "id"
    )
  )
  private Set<Authorities> authorities;

  private String email;
  private String firstName;
  private String lastName;
  private Date creation_date;
}

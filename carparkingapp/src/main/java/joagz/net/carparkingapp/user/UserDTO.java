package joagz.net.carparkingapp.user;

import java.util.Date;

public record UserDTO(
  Integer id,
  String username,
  Date creation_date,
  String email
) {}

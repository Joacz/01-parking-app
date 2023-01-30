package joagz.net.carparkingapp.mappers;

import java.util.function.Function;

import joagz.net.carparkingapp.user.UserDTO;
import joagz.net.carparkingapp.user.Users;

import org.springframework.stereotype.Service;

@Service
public class UserDTOMapper implements Function<Users, UserDTO> {

  @Override
  public UserDTO apply(Users t) {
    return new UserDTO(t.getId(), t.getUsername(), t.getCreation_date(),t.getEmail());
  }

}

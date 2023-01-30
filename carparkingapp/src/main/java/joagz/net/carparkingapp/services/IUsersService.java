package joagz.net.carparkingapp.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import joagz.net.carparkingapp.user.UserDTO;
import joagz.net.carparkingapp.user.Users;

public interface IUsersService {

  public List<UserDTO> findAll();

  public UserDTO findById(Integer id) throws NotFoundException;

  public void delete(UserDTO user);

  public void save(Users user);

}

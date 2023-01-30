package joagz.net.carparkingapp.db;

import java.util.List;
import java.util.stream.Collectors;
import joagz.net.carparkingapp.mappers.UserDTOMapper;
import joagz.net.carparkingapp.repo.UsersRepository;
import joagz.net.carparkingapp.services.IUsersService;
import joagz.net.carparkingapp.user.UserDTO;
import joagz.net.carparkingapp.user.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UsersJpa implements IUsersService {

  @Autowired
  private UsersRepository repo;

  @Autowired
  private UserDTOMapper userDTOMapper;

  @Override
  public List<UserDTO> findAll() {
    return repo
      .findAll()
      .stream()
      .map(userDTOMapper)
      .collect(Collectors.toList());
  }

  @Override
  public UserDTO findById(Integer id) throws NotFoundException {
    return repo
      .findById(id)
      .map(userDTOMapper)
      .orElseThrow(() -> new NotFoundException());
  }

  @Override
  public void delete(UserDTO user) {
    repo.delete(repo.findById(user.id()).get());
  }

  @Override
  public void save(Users user) {
    repo.save(user);
  }
}

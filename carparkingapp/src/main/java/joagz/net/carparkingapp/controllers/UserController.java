package joagz.net.carparkingapp.controllers;

import java.util.List;
import joagz.net.carparkingapp.db.UsersJpa;
import joagz.net.carparkingapp.user.UserDTO;
import joagz.net.carparkingapp.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UsersJpa usersJpa;

  @GetMapping("/")
  @CrossOrigin(origins = "*")
  public List<UserDTO> findAll(Authentication authentication) {
    return usersJpa.findAll();
  }

  @PostMapping("/")
  @CrossOrigin(origins = "*")
  public ResponseEntity<Users> create(
    @RequestBody Users body,
    Authentication authentication
  ) {
    try {
      usersJpa.save(body);
      return new ResponseEntity<Users>(body, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<Users>(body, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "*")
  public UserDTO findById(
    @PathVariable Integer id,
    Authentication authentication
  ) throws NotFoundException {
    return usersJpa.findById(id);
  }

  @DeleteMapping("/{id}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<UserDTO> delete(
    @PathVariable Integer id,
    Authentication authentication
  ) throws NotFoundException {
    UserDTO toDelete = usersJpa.findById(id);

    try {
      usersJpa.delete(toDelete);
      return new ResponseEntity<UserDTO>(toDelete, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<UserDTO>(toDelete, HttpStatus.NOT_FOUND);
    }
  }
}

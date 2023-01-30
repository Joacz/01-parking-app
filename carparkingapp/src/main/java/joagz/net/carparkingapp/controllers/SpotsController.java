package joagz.net.carparkingapp.controllers;

import java.util.List;
import joagz.net.carparkingapp.db.ParkingSpotsJpa;
import joagz.net.carparkingapp.models.ParkingSpots;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/spots")
public class SpotsController {

  @Autowired
  private ParkingSpotsJpa parkingSpotsJpa;

  @GetMapping("/")
  @CrossOrigin(origins = "*")
  public List<ParkingSpots> findAll(Authentication authentication) {
    return parkingSpotsJpa.findAll();
  }

  @GetMapping("/park/{id}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<ParkingSpots> useSpot(
    @PathVariable Integer id,
    Authentication authentication
  ) {
    try {
      ParkingSpots parking_spot = parkingSpotsJpa.findById(id);
      parking_spot.setOccuped(!parking_spot.getOccuped());
      parkingSpotsJpa.save(parking_spot);
      return new ResponseEntity<ParkingSpots>(HttpStatus.OK);
    } catch (NotFoundException e) {
      return new ResponseEntity<ParkingSpots>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/")
  @CrossOrigin(origins = "*")
  public ResponseEntity<ParkingSpots> create(
    @RequestBody ParkingSpots body,
    Authentication authentication
  ) {
    try {
      parkingSpotsJpa.save(body);
      return new ResponseEntity<ParkingSpots>(body, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<ParkingSpots>(body, HttpStatus.NOT_ACCEPTABLE);
    }
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "*")
  public ParkingSpots findById(
    @PathVariable Integer id,
    Authentication authentication
  ) throws NotFoundException {
    return parkingSpotsJpa.findById(id);
  }

  @GetMapping("/lot/{id}")
  @CrossOrigin(origins = "*")
  public List<ParkingSpots> findAllByParkingLots(
    @PathVariable Integer id,
    Authentication authentication
  ) {
    return parkingSpotsJpa.findAllByParkingLots(id);
  }

  @GetMapping("")
  @CrossOrigin(origins = "*")
  public List<ParkingSpots> findByOccuped(
    @RequestParam Boolean occuped,
    Authentication authentication
  ) {
    return parkingSpotsJpa.findByOccuped(occuped);
  }

  @DeleteMapping("/{id}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<ParkingSpots> delete(
    @PathVariable Integer id,
    Authentication authentication
  ) throws NotFoundException {
    ParkingSpots toDelete = parkingSpotsJpa.findById(id);

    parkingSpotsJpa.delete(toDelete);
    return new ResponseEntity<ParkingSpots>(toDelete, HttpStatus.OK);
  }
}

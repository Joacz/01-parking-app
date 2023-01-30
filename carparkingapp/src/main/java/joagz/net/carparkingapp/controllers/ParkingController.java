package joagz.net.carparkingapp.controllers;

import java.util.Date;
import java.util.List;
import joagz.net.carparkingapp.db.ParkingHistoryJpa;
import joagz.net.carparkingapp.db.ParkingLotsJpa;
import joagz.net.carparkingapp.db.ParkingSpotsJpa;
import joagz.net.carparkingapp.models.ParkingHistory;
import joagz.net.carparkingapp.models.ParkingLots;
import joagz.net.carparkingapp.models.ParkingSpots;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/lots")
public class ParkingController {

  @Autowired
  private ParkingLotsJpa parkingLotsJpa;

  @Autowired
  private ParkingSpotsJpa parkingSpotsJpa;

  @Autowired
  private ParkingHistoryJpa parkingHistoryJpa;

  @GetMapping("/")
  @CrossOrigin(origins = "*")
  public List<ParkingLots> findAll(Authentication authentication) {
    return parkingLotsJpa.findAll();
  }

  @PostMapping("/")
  @CrossOrigin(origins = "*")
  public ResponseEntity<ParkingLots> create(
    @RequestBody ParkingLots body,
    Authentication authentication
  ) {
    try {
      parkingLotsJpa.save(body);
      for (int i = 0; i <= body.getSpots(); i++) {
        ParkingSpots p_spot = new ParkingSpots(body, false);
        parkingSpotsJpa.save(p_spot);
      }

      return new ResponseEntity<ParkingLots>(body, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<ParkingLots>(body, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "*")
  public ParkingLots findAll(
    @PathVariable Integer id,
    Authentication authentication
  ) {
    return parkingLotsJpa.findById(id);
  }

  @DeleteMapping("/{id}")
  @CrossOrigin(origins = "*")
  public ResponseEntity<ParkingLots> delete(
    @PathVariable Integer id,
    Authentication authentication
  ) {
    ParkingLots toDelete = parkingLotsJpa.findById(id);

    try {
      parkingLotsJpa.delete(toDelete);
      return new ResponseEntity<ParkingLots>(toDelete, HttpStatus.OK);
    } catch (Exception e) {
      System.out.println(e);
      return new ResponseEntity<ParkingLots>(toDelete, HttpStatus.NOT_FOUND);
    }
  }
}

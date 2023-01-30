package joagz.net.carparkingapp.db;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import joagz.net.carparkingapp.models.ParkingHistory;
import joagz.net.carparkingapp.models.ParkingLots;
import joagz.net.carparkingapp.models.ParkingSpots;
import joagz.net.carparkingapp.repo.ParkingLotsRepository;
import joagz.net.carparkingapp.repo.ParkingSpotsRepository;
import joagz.net.carparkingapp.services.IParkingSpotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ParkingSpotsJpa implements IParkingSpotsService {

  @Autowired
  private ParkingSpotsRepository repo;

  @Autowired
  private ParkingLotsRepository lotsRepo;

  @Autowired
  private ParkingHistoryJpa parkingHistoryJpa;

  @Override
  public List<ParkingSpots> findAll() {
    return repo.findAll();
  }

  @Override
  public ParkingSpots findById(Integer id) throws NotFoundException {
    Optional<ParkingSpots> p_spot = repo.findById(id);
    if (!p_spot.isPresent()) {
      System.out.printf("Parking spot with ID: %d not found", id);
      throw new NotFoundException();
    }
    return p_spot.get();
  }

  @Override
  public void delete(ParkingSpots p_spot) {
    repo.delete(p_spot);
  }

  @Override
  public void save(ParkingSpots p_spot) {
    p_spot.setHistory(parkingHistoryJpa.save(new ParkingHistory()));
    repo.save(p_spot);
  }

  @Override
  public void park(ParkingSpots p_spot, Date start_time) {
    ParkingHistory history = parkingHistoryJpa.findById(
      p_spot.getHistory().getId()
    );
    history.setStart_time(start_time);
    parkingHistoryJpa.save(history);
    p_spot.setHistory(history);
    repo.save(p_spot);
  }

  @Override
  public void exit(ParkingSpots p_spot, Date end_time) {
    ParkingHistory history = parkingHistoryJpa.findById(
      p_spot.getHistory().getId()
    );
    history.setEnd_time(end_time);
    parkingHistoryJpa.save(history);
    p_spot.setHistory(history);
    repo.save(p_spot);
  }

  @Override
  public List<ParkingSpots> findAllByParkingLots(Integer parkingLot_id) {
    Optional<ParkingLots> lot = lotsRepo.findById(parkingLot_id);
    return repo.findAllByLot(lot.get());
  }

  @Override
  public List<ParkingSpots> findByOccuped(Boolean occuped) {
    return repo.findAllByOccuped(occuped);
  }
}

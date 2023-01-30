package joagz.net.carparkingapp.services;

import java.util.Date;
import java.util.List;
import joagz.net.carparkingapp.models.ParkingSpots;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface IParkingSpotsService {
  public List<ParkingSpots> findAll();

  public ParkingSpots findById(Integer id) throws NotFoundException;

  public void delete(ParkingSpots p_spot);

  public void save(ParkingSpots p_spot);

  public List<ParkingSpots> findAllByParkingLots(Integer parkingLot_id);

  public void exit(ParkingSpots p_spot, Date end_time);

  public void park(ParkingSpots p_spot, Date start_time);

  public List<ParkingSpots> findByOccuped(Boolean occuped);
}

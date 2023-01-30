package joagz.net.carparkingapp.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import joagz.net.carparkingapp.models.ParkingSpots;

public interface IParkingSpotsService {
  public List<ParkingSpots> findAll();

  public ParkingSpots findById(Integer id) throws NotFoundException;

  public void delete(ParkingSpots p_spot);

  public void save(ParkingSpots p_spot);

  public List<ParkingSpots> findAllByParkingLots(Integer parkingLot_id);

  public List<ParkingSpots> findByOccuped(Boolean occuped);
}

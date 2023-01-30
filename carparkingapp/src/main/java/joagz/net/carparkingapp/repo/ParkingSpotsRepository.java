package joagz.net.carparkingapp.repo;

import java.util.List;

import joagz.net.carparkingapp.models.ParkingLots;
import joagz.net.carparkingapp.models.ParkingSpots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotsRepository
  extends JpaRepository<ParkingSpots, Integer> {

  public List<ParkingSpots> findAllByLot(ParkingLots lot);
  public List<ParkingSpots> findAllByOccuped(Boolean occuped);
}

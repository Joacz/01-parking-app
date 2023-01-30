package joagz.net.carparkingapp.repo;

import java.util.List;
import joagz.net.carparkingapp.models.ParkingLots;
import joagz.net.carparkingapp.user.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLotsRepository
  extends JpaRepository<ParkingLots, Integer> {
  @Query(
    value = "select * from parking_lots where owner = ?;",
    nativeQuery = true
  )
  List<ParkingLots> findByOwner(Users owner);

  @Query(
    value = "select * from parking_lots where name = ?;",
    nativeQuery = true
  )
  List<ParkingLots> findByName(String name);
}

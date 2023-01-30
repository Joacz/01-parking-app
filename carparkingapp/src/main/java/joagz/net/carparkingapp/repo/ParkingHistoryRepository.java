package joagz.net.carparkingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import joagz.net.carparkingapp.models.ParkingHistory;

public interface ParkingHistoryRepository extends JpaRepository<ParkingHistory,Integer>{
}
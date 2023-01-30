package joagz.net.carparkingapp.services;

import java.util.List;

import joagz.net.carparkingapp.models.ParkingHistory;

public interface IParkingHistoryService {

  public List<ParkingHistory> findAll();

  public ParkingHistory findById(Integer id);

  public void delete(ParkingHistory p_history);

  public ParkingHistory save(ParkingHistory p_history);

}

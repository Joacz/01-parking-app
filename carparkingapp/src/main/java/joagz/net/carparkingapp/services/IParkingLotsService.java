package joagz.net.carparkingapp.services;

import java.util.List;

import joagz.net.carparkingapp.models.ParkingLots;

public interface IParkingLotsService {

  public List<ParkingLots> findAll();

  public ParkingLots findById(Integer id);

  public void delete(ParkingLots p_lot);

  public void save(ParkingLots p_lot);

}
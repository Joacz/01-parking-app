package joagz.net.carparkingapp.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import joagz.net.carparkingapp.models.ParkingLots;
import joagz.net.carparkingapp.repo.ParkingLotsRepository;
import joagz.net.carparkingapp.services.IParkingLotsService;

@Service
@Primary
public class ParkingLotsJpa implements IParkingLotsService {

  @Autowired
  private ParkingLotsRepository repo;

  @Override
  public List<ParkingLots> findAll() {
    return repo.findAll();
  }

  @Override
  public ParkingLots findById(Integer id) {
    Optional<ParkingLots> p_lot = repo.findById(id);
    return p_lot.get();
  }

  @Override
  public void delete(ParkingLots p_lot) {
    repo.delete(p_lot);
  }

  @Override
  public void save(ParkingLots p_lot) {
    repo.save(p_lot);
  }

}

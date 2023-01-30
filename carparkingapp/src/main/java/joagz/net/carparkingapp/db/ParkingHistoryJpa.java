package joagz.net.carparkingapp.db;

import java.util.List;
import java.util.Optional;
import joagz.net.carparkingapp.models.ParkingHistory;
import joagz.net.carparkingapp.repo.ParkingHistoryRepository;
import joagz.net.carparkingapp.services.IParkingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ParkingHistoryJpa implements IParkingHistoryService {

  @Autowired
  private ParkingHistoryRepository repo;

  @Override
  public List<ParkingHistory> findAll() {
    return repo.findAll();
  }

  @Override
  public ParkingHistory findById(Integer id) {
    Optional<ParkingHistory> p_history = repo.findById(id);
    return p_history.get();
  }

  @Override
  public void delete(ParkingHistory p_history) {
    repo.delete(p_history);
  }

  @Override
  public ParkingHistory save(ParkingHistory p_history) {
    repo.save(p_history);
    return p_history;
  }
}

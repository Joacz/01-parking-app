package joagz.net.carparkingapp.repo;

import joagz.net.carparkingapp.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository
  extends JpaRepository<Authorities, Integer> {}

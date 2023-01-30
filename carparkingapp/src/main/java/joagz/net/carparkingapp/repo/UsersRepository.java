package joagz.net.carparkingapp.repo;

import java.util.List;
import java.util.Optional;
import joagz.net.carparkingapp.user.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
  @EntityGraph(type = EntityGraphType.FETCH, attributePaths = { "authorities" })
  public Optional<Users> findByUsername(String username);

  @EntityGraph(type = EntityGraphType.FETCH, attributePaths = { "authorities" })
  public List<Users> findAll();
}

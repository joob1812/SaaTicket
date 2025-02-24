package sn.diamniadio.polytech.saaticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.diamniadio.polytech.saaticket.models.Location;
import sn.diamniadio.polytech.saaticket.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
  User findByUsernameAndPassword(String username, String password);
  User findByUsernameAndRole(String username, String role);
  User findByUsernameAndRoleAndLocation(String username, String role, Location location);
}
package sn.diamniadio.polytech.saaticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.diamniadio.polytech.saaticket.models.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByServiceId(Long serviceId);
}
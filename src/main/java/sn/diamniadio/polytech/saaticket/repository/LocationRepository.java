package sn.diamniadio.polytech.saaticket.repository;

import sn.diamniadio.polytech.saaticket.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByServiceModelId(Long serviceModelId);
}
package sn.diamniadio.polytech.saaticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.diamniadio.polytech.saaticket.models.Queue;

import java.util.Optional;

public interface QueueRepository extends JpaRepository<Queue, Long> {
  Optional<Queue> findByLocationId(Long locationId);
}
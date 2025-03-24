package sn.diamniadio.polytech.saaticket.repository;

import sn.diamniadio.polytech.saaticket.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
  Optional<Queue> findByLocationIdAndDate(Long locationId, LocalDate date);
  List<Queue> findByIsActiveTrue();
}
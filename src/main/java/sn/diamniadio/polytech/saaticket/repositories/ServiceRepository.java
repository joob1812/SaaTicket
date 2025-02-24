package sn.diamniadio.polytech.saaticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.diamniadio.polytech.saaticket.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
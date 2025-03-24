package sn.diamniadio.polytech.saaticket.repository;

import sn.diamniadio.polytech.saaticket.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
}

package sn.diamniadio.polytech.saaticket.repository;

import sn.diamniadio.polytech.saaticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByQueueIdAndStatus(Long queueId, Ticket.TicketStatus status);
    int countByQueueIdAndStatusIn(Long queueId, List<Ticket.TicketStatus> statuses);
}
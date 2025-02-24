package sn.diamniadio.polytech.saaticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.diamniadio.polytech.saaticket.models.Queue;
import sn.diamniadio.polytech.saaticket.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByQueueAndTicketNumberBetween(Queue queue, Integer ticketNumber, Integer ticketNumber2);
    List<Ticket> findByQueueIdAndIsServedFalseOrderByTicketNumberAsc(Long queueId);
}
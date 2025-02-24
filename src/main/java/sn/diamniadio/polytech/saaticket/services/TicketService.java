package sn.diamniadio.polytech.saaticket.services;

import sn.diamniadio.polytech.saaticket.models.Ticket;
import sn.diamniadio.polytech.saaticket.repositories.TicketRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getPendingTickets(Long queueId) {
        return ticketRepository.findByQueueIdAndIsServedFalseOrderByTicketNumberAsc(queueId);
    }

    public void markAsServed(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setIsServed(true);
        ticketRepository.save(ticket);
    }
}
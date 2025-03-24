package sn.diamniadio.polytech.saaticket.service;

import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.Ticket;
import sn.diamniadio.polytech.saaticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final QueueService queueService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, QueueService queueService) {
        this.ticketRepository = ticketRepository;
        this.queueService = queueService;
    }

    public Ticket createTicket(Long locationId) {
        Queue queue = queueService.getOrCreateQueueForToday(locationId);

        Ticket ticket = new Ticket();
        queue.setLastNumber(queue.getLastNumber() + 1);
        ticket.setTicketNumber(queue.getLastNumber());
        ticket.setTimestamp(LocalDateTime.now());
        ticket.setStatus(Ticket.TicketStatus.WAITING);
        ticket.setQueue(queue);

        queueService.getQueueById(queue.getId()); // Save the updated queue
        return ticketRepository.save(ticket);
    }

    public int getPositionInQueue(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            Queue queue = ticket.getQueue();
            int position = 0;

            // Count tickets with WAITING status that have a lower number
            for (Ticket t : queue.getTickets()) {
                if (t.getStatus() == Ticket.TicketStatus.WAITING && t.getTicketNumber() < ticket.getTicketNumber()) {
                    position++;
                }
            }

            return position + 1; // Add 1 because position is 0-based
        }
        return -1;
    }

    public int getPeopleAhead(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            Queue queue = ticket.getQueue();
            return countTicketsAhead(ticket, queue);
        }
        return -1;
    }

    private int countTicketsAhead(Ticket ticket, Queue queue) {
        int count = 0;
        for (Ticket t : queue.getTickets()) {
            if (t.getStatus() == Ticket.TicketStatus.WAITING &&
                    t.getTicketNumber() < ticket.getTicketNumber()) {
                count++;
            }
        }
        return count;
    }

    public int countWaitingTickets(Long queueId) {
        return ticketRepository.countByQueueIdAndStatusIn(queueId,
                Arrays.asList(Ticket.TicketStatus.WAITING));
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
}

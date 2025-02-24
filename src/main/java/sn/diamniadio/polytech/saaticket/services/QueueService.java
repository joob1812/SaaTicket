package sn.diamniadio.polytech.saaticket.services;

import sn.diamniadio.polytech.saaticket.models.*;
import sn.diamniadio.polytech.saaticket.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class QueueService {
    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Long locationId) {
        Queue queue = queueRepository.findByLocationId(locationId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        Ticket ticket = new Ticket();
        ticket.setTicketNumber(queue.getLastNumber() + 1);
        ticket.setCreationTime(LocalDateTime.now());
        ticket.setQueue(queue);

        queue.setLastNumber(queue.getLastNumber() + 1);
        queueRepository.save(queue);

        return ticketRepository.save(ticket);
    }


    public TicketInfo getTicketInfo(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Queue queue = ticket.getQueue();
        long position = ticketRepository.countByQueueAndTicketNumberBetween(
                queue, queue.getCurrentNumber(), ticket.getTicketNumber());

        return new TicketInfo(
                ticket.getTicketNumber(),
                position,
                position - 1,
                queue.getCurrentNumber()
        );
    }

    public void nextClient(Long queueId) {
        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));
        queue.setCurrentNumber(queue.getCurrentNumber() + 1);
        queueRepository.save(queue);
    }

    public void previousClient(Long queueId) {
        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));
        if (queue.getCurrentNumber() > 0) {
            queue.setCurrentNumber(queue.getCurrentNumber() - 1);
            queueRepository.save(queue);
        }
    }
}


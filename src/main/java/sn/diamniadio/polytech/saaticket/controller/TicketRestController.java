package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.dto.TicketInfoDTO;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.Ticket;
import sn.diamniadio.polytech.saaticket.service.QueueService;
import sn.diamniadio.polytech.saaticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TicketRestController {

    private final TicketService ticketService;
    private final QueueService queueService;

    @Autowired
    public TicketRestController(TicketService ticketService, QueueService queueService) {
        this.ticketService = ticketService;
        this.queueService = queueService;
    }

    @PostMapping("/location/{locationId}/ticket")
    public ResponseEntity<Ticket> createTicket(@PathVariable Long locationId) {
        Ticket ticket = ticketService.createTicket(locationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketInfoDTO> getTicketInfo(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);

        if (ticket != null) {
            int position = ticketService.getPositionInQueue(id);
            int peopleAhead = ticketService.getPeopleAhead(id);
            Queue queue = ticket.getQueue();

            TicketInfoDTO ticketInfo = new TicketInfoDTO(
                    ticket.getTicketNumber(),
                    position,
                    peopleAhead,
                    queue.getCurrentNumber()
            );

            return ResponseEntity.ok(ticketInfo);
        }

        return ResponseEntity.notFound().build();
    }

    // Nouveau endpoint pour récupérer à la fois le ticket et ses informations détaillées
    @GetMapping("/ticket/{id}/full")
    public ResponseEntity<Object> getFullTicketInfo(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);

        if (ticket != null) {
            int position = ticketService.getPositionInQueue(id);
            int peopleAhead = ticketService.getPeopleAhead(id);
            Queue queue = ticket.getQueue();

            TicketInfoDTO ticketInfo = new TicketInfoDTO(
                    ticket.getTicketNumber(),
                    position,
                    peopleAhead,
                    queue.getCurrentNumber()
            );

            // Créer un objet qui contient à la fois le ticket et les informations détaillées
            // Vous pourriez créer un DTO spécifique pour cela, mais ici on utilise un map simple
            java.util.Map<String, Object> fullInfo = new java.util.HashMap<>();
            fullInfo.put("ticket", ticket);
            fullInfo.put("ticketInfo", ticketInfo);

            return ResponseEntity.ok(fullInfo);
        }

        return ResponseEntity.notFound().build();
    }
}
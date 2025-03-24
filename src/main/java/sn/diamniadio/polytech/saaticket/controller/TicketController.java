package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.dto.TicketInfoDTO;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.Ticket;
import sn.diamniadio.polytech.saaticket.service.QueueService;
import sn.diamniadio.polytech.saaticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    private final TicketService ticketService;
    private final QueueService queueService;

    @Autowired
    public TicketController(TicketService ticketService, QueueService queueService) {
        this.ticketService = ticketService;
        this.queueService = queueService;
    }

    @PostMapping("/location/{locationId}/ticket")
    public String createTicket(@PathVariable Long locationId) {
        Ticket ticket = ticketService.createTicket(locationId);
        return "redirect:/ticket/" + ticket.getId();
    }

    @GetMapping("/ticket/{id}")
    public String showTicket(@PathVariable Long id, Model model) {
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

            model.addAttribute("ticketInfo", ticketInfo);
            return "ticket";
        }

        return "redirect:/";
    }
}
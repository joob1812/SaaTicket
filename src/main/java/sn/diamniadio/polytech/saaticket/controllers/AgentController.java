package sn.diamniadio.polytech.saaticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.diamniadio.polytech.saaticket.models.Queue;
import sn.diamniadio.polytech.saaticket.models.User;
import sn.diamniadio.polytech.saaticket.services.QueueService;
import sn.diamniadio.polytech.saaticket.services.TicketService;

@Controller
@RequestMapping("/agent")
@PreAuthorize("hasRole('AGENT')")
public class AgentController {
    @Autowired
    private QueueService queueService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/queue")
    public String queueManagement(Model model) {
        // Assuming agent is associated with a location/queue
        User agent = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Queue queue = agent.getLocation().getQueue();

        model.addAttribute("currentNumber", queue.getCurrentNumber());
        model.addAttribute("pendingTickets", ticketService.getPendingTickets(queue.getId()));
        return "agent/queue-management";
    }

    @PostMapping("/next")
    @ResponseBody
    public void nextClient(@RequestParam Long queueId) {
        queueService.nextClient(queueId);
    }

    @PostMapping("/previous")
    @ResponseBody
    public void previousClient(@RequestParam Long queueId) {
        queueService.previousClient(queueId);
    }
}

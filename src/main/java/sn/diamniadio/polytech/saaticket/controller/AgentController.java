package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.User;
import sn.diamniadio.polytech.saaticket.service.LocationService;
import sn.diamniadio.polytech.saaticket.service.QueueService;
import sn.diamniadio.polytech.saaticket.service.TicketService;
import sn.diamniadio.polytech.saaticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/agent")
public class AgentController {
    private final QueueService queueService;
    private final TicketService ticketService;
    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public AgentController(QueueService queueService, TicketService ticketService,
                           LocationService locationService, UserService userService) {
        this.queueService = queueService;
        this.ticketService = ticketService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Get current authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Récupérer l'utilisateur connecté
        User agent = userService.getUserByUsername(auth.getName());

        if (agent != null && agent.getLocation() != null) {
            // Récupérer la localisation de l'agent
            Location location = agent.getLocation();

            // Récupérer ou créer la file d'attente du jour pour cette localisation
            Queue queue = queueService.getOrCreateQueueForToday(location.getId());

            // Compter les personnes en attente
            int waitingCount = ticketService.countWaitingTickets(queue.getId());

            model.addAttribute("queue", queue);
            model.addAttribute("location", location);
            model.addAttribute("waitingCount", waitingCount);
        } else {
            // Gérer le cas où l'utilisateur n'a pas de localisation attribuée
            model.addAttribute("error", "Aucune localisation attribuée à cet agent");
        }

        return "agent/dashboard";
    }

    @PostMapping("/queue/{id}/next")
    public String nextClient(@PathVariable Long id) {
        queueService.incrementCurrentNumber(id);
        return "redirect:/agent/dashboard";
    }

    @PostMapping("/queue/{id}/previous")
    public String previousClient(@PathVariable Long id) {
        queueService.decrementCurrentNumber(id);
        return "redirect:/agent/dashboard";
    }
}
package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.User;
import sn.diamniadio.polytech.saaticket.service.LocationService;
import sn.diamniadio.polytech.saaticket.service.QueueService;
import sn.diamniadio.polytech.saaticket.service.TicketService;
import sn.diamniadio.polytech.saaticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/agent")
@CrossOrigin(origins = "*")
public class AgentRestController {
    private final QueueService queueService;
    private final TicketService ticketService;
    private final LocationService locationService;
    private final UserService userService;

    @Autowired
    public AgentRestController(QueueService queueService, TicketService ticketService,
                               LocationService locationService, UserService userService) {
        this.queueService = queueService;
        this.ticketService = ticketService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboardData() {
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

            Map<String, Object> response = new HashMap<>();
            response.put("queue", queue);
            response.put("location", location);
            response.put("waitingCount", waitingCount);

            return ResponseEntity.ok(response);
        } else {
            // Gérer le cas où l'utilisateur n'a pas de localisation attribuée
            Map<String, String> error = new HashMap<>();
            error.put("error", "Aucune localisation attribuée à cet agent");
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/queue/{id}/next")
    public ResponseEntity<Queue> nextClient(@PathVariable Long id) {
        Queue updatedQueue = queueService.incrementCurrentNumber(id);
        return ResponseEntity.ok(updatedQueue);
    }

    @PostMapping("/queue/{id}/previous")
    public ResponseEntity<Queue> previousClient(@PathVariable Long id) {
        Queue updatedQueue = queueService.decrementCurrentNumber(id);
        return ResponseEntity.ok(updatedQueue);
    }
}
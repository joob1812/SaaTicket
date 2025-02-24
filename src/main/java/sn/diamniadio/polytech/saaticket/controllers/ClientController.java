package sn.diamniadio.polytech.saaticket.controllers;

import sn.diamniadio.polytech.saaticket.models.Ticket;
import sn.diamniadio.polytech.saaticket.models.TicketInfo;
import sn.diamniadio.polytech.saaticket.repositories.LocationRepository;
import sn.diamniadio.polytech.saaticket.repositories.ServiceRepository;
import sn.diamniadio.polytech.saaticket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private QueueService queueService;

    @GetMapping("/")
    public String selectService(Model model) {
        model.addAttribute("services", serviceRepository.findAll());
        return "client/service-selection";
    }

    @GetMapping("/locations/{serviceId}")
    public String selectLocation(@PathVariable Long serviceId, Model model) {
        model.addAttribute("locations", locationRepository.findByServiceId(serviceId));
        return "client/location-selection";
    }

    @PostMapping("/ticket/{locationId}")
    public String createTicket(@PathVariable Long locationId, Model model) {
        Ticket ticket = queueService.createTicket(locationId);
        TicketInfo ticketInfo = queueService.getTicketInfo(ticket.getId());
        model.addAttribute("ticketInfo", ticketInfo);
        return "client/ticket-info";
    }
}

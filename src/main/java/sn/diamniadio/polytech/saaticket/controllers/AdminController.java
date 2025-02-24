package sn.diamniadio.polytech.saaticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.diamniadio.polytech.saaticket.models.Location;
import sn.diamniadio.polytech.saaticket.models.Queue;
import sn.diamniadio.polytech.saaticket.models.Service;
import sn.diamniadio.polytech.saaticket.repositories.LocationRepository;
import sn.diamniadio.polytech.saaticket.repositories.QueueRepository;
import sn.diamniadio.polytech.saaticket.repositories.ServiceRepository;

@Controller
@RequestMapping("/admin")//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("queues", queueRepository.findAll());
        model.addAttribute("services", serviceRepository.findAll());
        return "admin/dashboard";
    }

    @PostMapping("/service")
    public String createService(@ModelAttribute Service service) {
        serviceRepository.save(service);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/location")
    public String createLocation(@ModelAttribute Location location) {
        Queue queue = new Queue();
        location.setQueue(queue);
        queue.setLocation(location);
        locationRepository.save(location);
        return "redirect:/admin/dashboard";
    }
}

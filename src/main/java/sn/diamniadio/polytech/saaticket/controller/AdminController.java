package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.dto.QueueStatusDTO;
import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.ServiceModel;
import sn.diamniadio.polytech.saaticket.model.User;
import sn.diamniadio.polytech.saaticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.diamniadio.polytech.saaticket.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ServiceService serviceService;
    private final LocationService locationService;
    private final QueueService queueService;
    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public AdminController(ServiceService serviceService,
                           LocationService locationService,
                           QueueService queueService,
                           TicketService ticketService,
                           UserService userService) {
        this.serviceService = serviceService;
        this.locationService = locationService;
        this.queueService = queueService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        List<Queue> activeQueues = queueService.getAllActiveQueues();
        List<QueueStatusDTO> queueStatuses = new ArrayList<>();

        for (Queue queue : activeQueues) {
            Location location = queue.getLocation();
            ServiceModel serviceModel = location.getService();
            int waitingCount = ticketService.countWaitingTickets(queue.getId());

            QueueStatusDTO status = new QueueStatusDTO(
                    queue.getId(),
                    serviceModel.getName(),
                    location.getName(),
                    queue.getCurrentNumber(),
                    waitingCount
            );

            queueStatuses.add(status);
        }

        model.addAttribute("queueStatuses", queueStatuses);
        return "admin/dashboard";
    }

    // Services management
    @GetMapping("/services")
    public String listServices(Model model) {
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        model.addAttribute("services", serviceModels);
        return "admin/services";
    }

    @GetMapping("/services/add")
    public String showAddServiceForm(Model model) {
        model.addAttribute("service", new ServiceModel());
        return "admin/service-form";
    }

    @PostMapping("/services/save")
    public String saveService(@ModelAttribute ServiceModel serviceModel) {
        serviceService.saveService(serviceModel);
        return "redirect:/admin/services";
    }

    @GetMapping("/services/edit/{id}")
    public String showEditServiceForm(@PathVariable Long id, Model model) {
        ServiceModel serviceModel = serviceService.getServiceById(id);
        model.addAttribute("service", serviceModel);
        return "admin/service-form";
    }

    @GetMapping("/services/delete/{id}")
    public String deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return "redirect:/admin/services";
    }

    // Locations management
    @GetMapping("/locations")
    public String listLocations(Model model) {
        List<Location> locations = locationService.getAllLocations();
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        model.addAttribute("locations", locations);
        model.addAttribute("services", serviceModels);
        return "admin/locations";
    }

    @GetMapping("/locations/add")
    public String showAddLocationForm(Model model) {
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        model.addAttribute("location", new Location());
        model.addAttribute("services", serviceModels);
        return "admin/location-form";
    }

    @PostMapping("/locations/save")
    public String saveLocation(@ModelAttribute Location location) {
        locationService.saveLocation(location);
        return "redirect:/admin/locations";
    }

    @GetMapping("/locations/edit/{id}")
    public String showEditLocationForm(@PathVariable Long id, Model model) {
        Location location = locationService.getLocationById(id);
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        model.addAttribute("location", location);
        model.addAttribute("services", serviceModels);
        return "admin/location-form";
    }

    @GetMapping("/locations/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return "redirect:/admin/locations";
    }

    // Users management
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("user", new User());
        model.addAttribute("locations", locations);
        model.addAttribute("roles", User.Role.values());
        return "admin/user-form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("user", user);
        model.addAttribute("locations", locations);
        model.addAttribute("roles", User.Role.values());
        return "admin/user-form";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
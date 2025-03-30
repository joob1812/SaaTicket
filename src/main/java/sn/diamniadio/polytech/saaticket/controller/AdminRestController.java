package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.dto.QueueStatusDTO;
import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.model.ServiceModel;
import sn.diamniadio.polytech.saaticket.model.User;
import sn.diamniadio.polytech.saaticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // Permettre les requêtes cross-origin (à ajuster selon votre environnement)
public class AdminRestController {

    private final ServiceService serviceService;
    private final LocationService locationService;
    private final QueueService queueService;
    private final TicketService ticketService;
    private final UserService userService;

    @Autowired
    public AdminRestController(ServiceService serviceService,
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
    public ResponseEntity<List<QueueStatusDTO>> getDashboardData() {
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

        return ResponseEntity.ok(queueStatuses);
    }

    // Services management
    @GetMapping("/services")
    public ResponseEntity<List<ServiceModel>> getAllServices() {
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        return ResponseEntity.ok(serviceModels);
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<ServiceModel> getServiceById(@PathVariable Long id) {
        ServiceModel serviceModel = serviceService.getServiceById(id);
        if (serviceModel != null) {
            return ResponseEntity.ok(serviceModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/services")
    public ResponseEntity<ServiceModel> createService(@RequestBody ServiceModel serviceModel) {
        ServiceModel savedService = serviceService.saveService(serviceModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<ServiceModel> updateService(@PathVariable Long id, @RequestBody ServiceModel serviceModel) {
        serviceModel.setId(id); // Ensure the ID is set correctly
        ServiceModel updatedService = serviceService.saveService(serviceModel);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    // Locations management
    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location savedLocation = locationService.saveLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        location.setId(id);
        Location updatedLocation = locationService.saveLocation(location);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    // Users management
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
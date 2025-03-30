package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.model.ServiceModel;
import sn.diamniadio.polytech.saaticket.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HomeRestController {

    private final ServiceService serviceService;

    @Autowired
    public HomeRestController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceModel>> getServices() {
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        return ResponseEntity.ok(serviceModels);
    }
}
package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ServiceRestController {

    private final LocationService locationService;

    @Autowired
    public ServiceRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/service/{id}/locations")
    public ResponseEntity<List<Location>> getServiceLocations(@PathVariable Long id) {
        List<Location> locations = locationService.getLocationsByServiceId(id);
        return ResponseEntity.ok(locations);
    }
}
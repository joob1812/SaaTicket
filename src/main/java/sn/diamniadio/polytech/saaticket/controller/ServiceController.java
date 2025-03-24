package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class ServiceController {

    private final LocationService locationService;

    @Autowired
    public ServiceController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/service/{id}")
    public String showServiceLocations(@PathVariable Long id, Model model) {
        List<Location> locations = locationService.getLocationsByServiceId(id);
        model.addAttribute("locations", locations);
        model.addAttribute("serviceId", id);
        return "locations";
    }
}
package sn.diamniadio.polytech.saaticket.controller;

import sn.diamniadio.polytech.saaticket.model.ServiceModel;
import sn.diamniadio.polytech.saaticket.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ServiceService serviceService;

    @Autowired
    public HomeController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ServiceModel> serviceModels = serviceService.getAllServices();
        model.addAttribute("services", serviceModels);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
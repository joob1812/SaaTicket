package sn.diamniadio.polytech.saaticket.service;

import sn.diamniadio.polytech.saaticket.model.ServiceModel;
import sn.diamniadio.polytech.saaticket.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceModel> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceModel getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    public ServiceModel saveService(ServiceModel serviceModel) {
        return serviceRepository.save(serviceModel);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
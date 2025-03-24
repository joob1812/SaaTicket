package sn.diamniadio.polytech.saaticket.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceModel serviceModel;

    @OneToMany(mappedBy = "location")
    private List<Queue> queues;

    @OneToMany(mappedBy = "location")
    private List<User> agents;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public ServiceModel getService() { return serviceModel; }
    public void setService(ServiceModel serviceModel) { this.serviceModel = serviceModel; }
    public List<Queue> getQueues() { return queues; }
    public void setQueues(List<Queue> queues) { this.queues = queues; }
    public List<User> getAgents() { return agents; }
    public void setAgents(List<User> agents) { this.agents = agents; }
}
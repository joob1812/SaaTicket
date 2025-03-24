package sn.diamniadio.polytech.saaticket.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;

  @OneToMany(mappedBy = "serviceModel")
  private List<Location> locations;

  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public List<Location> getLocations() { return locations; }
  public void setLocations(List<Location> locations) { this.locations = locations; }
}
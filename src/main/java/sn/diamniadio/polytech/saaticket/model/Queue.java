package sn.diamniadio.polytech.saaticket.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int currentNumber;
    private int lastNumber;
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "queue")
    private List<Ticket> tickets;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public int getCurrentNumber() { return currentNumber; }
    public void setCurrentNumber(int currentNumber) { this.currentNumber = currentNumber; }
    public int getLastNumber() { return lastNumber; }
    public void setLastNumber(int lastNumber) { this.lastNumber = lastNumber; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}

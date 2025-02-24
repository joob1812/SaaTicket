package sn.diamniadio.polytech.saaticket.models;

//import java.util.List;
//import lombok.Data;

public class TicketInfo {
    private Integer ticketNumber;
    private Long position;
    private Long peopleAhead;
    private Integer currentNumber;

    public TicketInfo() {}

    public TicketInfo(Integer ticketNumber, Long position, Long peopleAhead, Integer currentNumber) {
        this.ticketNumber = ticketNumber;
        this.position = position;
        this.peopleAhead = peopleAhead;
        this.currentNumber = currentNumber;
    }

    // Getters
    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public Long getPosition() {
        return position;
    }

    public Long getPeopleAhead() {
        return peopleAhead;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    // Setters
    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public void setPeopleAhead(Long peopleAhead) {
        this.peopleAhead = peopleAhead;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }
}
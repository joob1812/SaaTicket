package sn.diamniadio.polytech.saaticket.dto;

public class TicketInfoDTO {
    private int ticketNumber;
    private int position;
    private int peopleAhead;
    private int currentNumber;

    public TicketInfoDTO(int ticketNumber, int position, int peopleAhead, int currentNumber) {
        this.ticketNumber = ticketNumber;
        this.position = position;
        this.peopleAhead = peopleAhead;
        this.currentNumber = currentNumber;
    }

    // Getters and setters
    public int getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(int ticketNumber) { this.ticketNumber = ticketNumber; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
    public int getPeopleAhead() { return peopleAhead; }
    public void setPeopleAhead(int peopleAhead) { this.peopleAhead = peopleAhead; }
    public int getCurrentNumber() { return currentNumber; }
    public void setCurrentNumber(int currentNumber) { this.currentNumber = currentNumber; }
}

package sn.diamniadio.polytech.saaticket.dto;

public class QueueStatusDTO {
    private Long queueId;
    private String serviceName;
    private String locationName;
    private int currentNumber;
    private int waitingCount;

    public QueueStatusDTO(Long queueId, String serviceName, String locationName,
                          int currentNumber, int waitingCount) {
        this.queueId = queueId;
        this.serviceName = serviceName;
        this.locationName = locationName;
        this.currentNumber = currentNumber;
        this.waitingCount = waitingCount;
    }

    // Getters and setters
    public Long getQueueId() { return queueId; }
    public void setQueueId(Long queueId) { this.queueId = queueId; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    public int getCurrentNumber() { return currentNumber; }
    public void setCurrentNumber(int currentNumber) { this.currentNumber = currentNumber; }
    public int getWaitingCount() { return waitingCount; }
    public void setWaitingCount(int waitingCount) { this.waitingCount = waitingCount; }
}
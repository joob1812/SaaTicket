package sn.diamniadio.polytech.saaticket.service;

import sn.diamniadio.polytech.saaticket.model.Location;
import sn.diamniadio.polytech.saaticket.model.Queue;
import sn.diamniadio.polytech.saaticket.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    private final QueueRepository queueRepository;

    @Autowired
    public QueueService(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    public Queue getOrCreateQueueForToday(Long locationId) {
        LocalDate today = LocalDate.now();
        Optional<Queue> existingQueue = queueRepository.findByLocationIdAndDate(locationId, today);

        if (existingQueue.isPresent()) {
            return existingQueue.get();
        } else {
            Queue newQueue = new Queue();
            newQueue.setDate(today);
            newQueue.setCurrentNumber(0);
            newQueue.setLastNumber(0);
            newQueue.setActive(true);

            // Set location
            Location location = new Location();
            location.setId(locationId);
            newQueue.setLocation(location);

            return queueRepository.save(newQueue);
        }
    }

    public Queue incrementCurrentNumber(Long queueId) {
        Queue queue = queueRepository.findById(queueId).orElse(null);
        if (queue != null) {
            queue.setCurrentNumber(queue.getCurrentNumber() + 1);
            return queueRepository.save(queue);
        }
        return null;
    }

    public Queue decrementCurrentNumber(Long queueId) {
        Queue queue = queueRepository.findById(queueId).orElse(null);
        if (queue != null && queue.getCurrentNumber() > 0) {
            queue.setCurrentNumber(queue.getCurrentNumber() - 1);
            return queueRepository.save(queue);
        }
        return null;
    }

    public List<Queue> getAllActiveQueues() {
        return queueRepository.findByIsActiveTrue();
    }

    public Queue getQueueById(Long id) {
        return queueRepository.findById(id).orElse(null);
    }
}

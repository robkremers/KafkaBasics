package kafka.services;


import kafka.components.SpeedCalculator;
import kafka.entities.SensorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
	@Autowired
    SpeedCalculator speedCalculator;

    @KafkaListener(topics = {"cameratopic1" , "cameratopic2"})
    public void receive(@Payload SensorRecord sensorRecord,
                        @Headers MessageHeaders headers) {
        System.out.println("received message="+ sensorRecord.toString());
        speedCalculator.handleRecord(sensorRecord);
    }

}
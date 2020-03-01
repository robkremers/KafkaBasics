package kafka.services;


import kafka.components.SpeedCalculator;
import kafka.entities.SensorRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Log logger = LogFactory.getLog(Receiver.class);

    @Autowired
    SpeedCalculator speedCalculator;

    @KafkaListener(topics = {"cameratopic1" , "cameratopic2"})
    public void receive(@Payload SensorRecord sensorRecord,
                        @Headers MessageHeaders headers) {
        logger.info("received message="+ sensorRecord.toString());
        speedCalculator.handleRecord(sensorRecord);
    }

}
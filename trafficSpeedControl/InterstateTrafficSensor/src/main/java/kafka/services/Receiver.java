package kafka.services;


import kafka.entities.SensorRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * This receiver is just for test purposes.
 * The processing of the outgoing data will be processed in a separate application.
 */
@Service
public class Receiver {

    private static final Log logger = LogFactory.getLog(Receiver.class);

    @KafkaListener(topics = {"cameratopic1" , "cameratopic2"})
    public void receive(@Payload SensorRecord SensorRecord,
                        @Headers MessageHeaders headers) {
        logger.info("received message="+ SensorRecord.toString());
    }

}
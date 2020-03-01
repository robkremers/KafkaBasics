package kafka.services;


import kafka.entities.SpeedRecord;
import kafka.services.OwnerService;
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
	OwnerService ownerService;

    @KafkaListener(topics = "${app.topic.tofasttopic}")
    public void receive(@Payload SpeedRecord speedRecord,
                        @Headers MessageHeaders headers) {
    	logger.info("*** Find owner of "+ speedRecord.toString());
    	ownerService.findOwner(speedRecord.licencePlate, speedRecord.getSpeed());
    }
}
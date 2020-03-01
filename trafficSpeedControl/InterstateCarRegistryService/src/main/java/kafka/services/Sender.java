package kafka.services;

import kafka.entities.FeeRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Log logger = LogFactory.getLog(Sender.class);

    @Autowired
    private KafkaTemplate<String, FeeRecord> kafkaTemplate;
    
    @Value("${app.topic.feetopic}")
    private String topic;

    public void send(FeeRecord feeRecord){

        logger.info("*** Sending feeRecord " + feeRecord);
        kafkaTemplate.send(topic, feeRecord);
    }
}

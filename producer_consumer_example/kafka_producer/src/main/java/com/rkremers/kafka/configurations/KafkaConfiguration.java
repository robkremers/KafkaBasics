package com.rkremers.kafka.configurations;

import com.rkremers.kafka.entities.Book;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There are two ways to implement the serialization of an instance of an entity, in this case Book.
 * Method 1:
 * - Create a serializers.BookSerializer class that implements Serializer<Book>.
 * - Add the class to the ProducerConfig as value for ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG.
 * - Return new DefaultKafkaProducerFactory<>(configuration)
 *
 * Method 2:
 * - Add the standard class JsonSerializer to the ProducerConfig as value for ProducerConfig
 * .VALUE_SERIALIZER_CLASS_CONFIG.
 * - Use an expanded constructor of class DefaultKafkaProducerFactory, adding new JsonSerializer<Book>().
 *
 * Both methods have been tested successfully.
 */
@Configuration
public class KafkaConfiguration {

    @Bean
    public ProducerFactory<String, Book> producerFactory() {
        Map<String, Object> configuration = new HashMap<>();

        configuration.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configuration.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, BookSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(configuration);

        configuration.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Some additional possibilities. TODO: Needs further explanation.
        /**
         * Producer can choose whether to retry.
         * 0: Do not retry; loses messages on error.
         * >o: Retry, might result in duplicates on error.
         * Alternative:
         * - Choose Idempotence TODO: howto.
         */
        configuration.put(ProducerConfig.RETRIES_CONFIG, "1");
        /**
         * Producer can choose the acknowledgement level:
         * 0: Fire-and-forget
         * 1: Waits for 1 broker to acknowledge (default)
         * ALL: Waits for all replica brokers to acknowledge. Enforces synchronization over leader, followers.
         */
        configuration.put(ProducerConfig.ACKS_CONFIG, "1");
        /**
         * So although SSL is not being used in Kafka reference to a protocol is still using the 'SSL' term.
         * But the protocol can be anything.
         */
        configuration.put(SslConfigs.SSL_PROTOCOL_CONFIG, "TLSv1.2");
//        configuration.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,"es-cert.jks");
//        configuration.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "passsword");
//        configuration.put(SaslConfigs.DEFAULT_SASL_MECHANISM, "PLAIN"); // vs. GSSAPI ?


        return new DefaultKafkaProducerFactory<>(configuration, new StringSerializer(),
                new JsonSerializer<Book>());
    }

    @Bean
    public KafkaTemplate<String, Book> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}

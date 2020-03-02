package com.rkremers.kafka.configurations;

import com.rkremers.kafka.deserializers.BookDeserializer;
import com.rkremers.kafka.entities.Book;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

/**
 * Note:
 * The naming of the methods can cause errors, that would require diving deeper in the Kafka setup.
 * e.g. instead of kafkaListenerContainerFactory naming bookKafkaListenerContainerFactory creates errors.
 * Suddenly the bean bookConsumerFactory can not be found anymore.
 * Error:
 * Parameter 1 of method kafkaListenerContainerFactory in org.springframework.boot.autoconfigure.kafka
 * .KafkaAnnotationDrivenConfiguration required a bean of type 'org.springframework.kafka.core.ConsumerFactory' that
 * could not be found.
 *
 * The following candidates were found but could not be injected:
 * 	- Bean method 'kafkaConsumerFactory' in 'KafkaAutoConfiguration' not loaded because @ConditionalOnBean (types: org
 * 	.springframework.kafka.core.ConsumerFactory; SearchStrategy: all) found beans of type 'org.springframework.kafka
 * 	.core.ConsumerFactory' bookConsumerFactory
 * 	- User-defined bean method 'bookConsumerFactory' in 'KafkaConfiguration'
 */
@Configuration
// The following Annotation enables listening to Kafka events.
@EnableKafka
@EnableScheduling
public class KafkaConfiguration {

    @Bean (name = "bookConsumerFactory")
    public ConsumerFactory<String, Book> bookConsumerFactory() {
        Map<String, Object> configuration  = new HashMap<>();

         /**
          * The following is needed in case of different consumer factories for the same topic.
          * Reasons:
          * Multiple consumers can share the load.
          * If one consumer dies another one can take over the processing.
          *
          * e.g. in case of multiple partitions multiple consumers can choose to load from different
          * partitions and / or from different offsets ( points in the topic list).
          *
          *
          */
        configuration.put(ConsumerConfig.GROUP_ID_CONFIG, "bookConfigId");

        configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Works.
        configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, BookDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configuration);

        // For some reason this does not work; something for future analysis.
//        configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//                JsonDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(configuration, new StringDeserializer(),
//                new org.springframework.kafka.support.serializer.JsonDeserializer<Book>() );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Book> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Book> concurrentKafkaListenerContainerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        // I need to use a setter. There is no constructor that accepts the consumerFactory.
        concurrentKafkaListenerContainerFactory.setConsumerFactory( bookConsumerFactory());
        concurrentKafkaListenerContainerFactory.setConcurrency(4);
        return concurrentKafkaListenerContainerFactory;
    }

}

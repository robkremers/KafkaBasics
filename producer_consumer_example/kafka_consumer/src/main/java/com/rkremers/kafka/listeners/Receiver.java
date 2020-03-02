package com.rkremers.kafka.listeners;

import com.rkremers.kafka.entities.Book;
import com.rkremers.kafka.entities.Todo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @KafkaListener( groupId = "bookConfigId"
                   , topics = {"bookTopic"}
                   , containerFactory = "kafkaListenerContainerFactory")
    public void receive(@Payload Book book,
                        @Headers MessageHeaders headers) {
        headers.keySet().forEach(key -> System.out.println(key + ": " + headers.get(key)));
        System.out.println("Received message: " + book.toString());

        Todo todo = new Todo();
        todo
    }
}

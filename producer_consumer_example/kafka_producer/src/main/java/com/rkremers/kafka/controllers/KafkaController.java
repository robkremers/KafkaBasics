package com.rkremers.kafka.controllers;

import com.rkremers.kafka.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private KafkaTemplate<String, Book> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate<String, Book> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public void post(@RequestBody Book book) {
        System.out.println("book = " + book.toString());
        kafkaTemplate.send("bookTopic", book);
        kafkaTemplate.flush();
    }
}

package com.rkremers.kafka.deserializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkremers.kafka.entities.Book;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class BookDeserializer implements Deserializer<Book> {
    @Override
    public void configure(Map<String, ?> configs,
                          boolean isKey) {
    }

    @Override
    public Book deserialize(String s,
                            byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = null;
        try {
            book = objectMapper.readValue(bytes, Book.class);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return book;
    }

//    @Override
//    public Book deserialize(String topic,
//                            Headers headers,
//                            byte[] data) {
//        return null;
//    }

    @Override
    public void close() {
    }
}

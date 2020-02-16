package com.rkremers.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkremers.kafka.entities.Book;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class BookSerializer implements Serializer<Book> {
    @Override
    public byte[] serialize(String s,
                            Book book) {
        byte[] serializedBytes = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            serializedBytes = objectMapper.writeValueAsString(book).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serializedBytes;
    }

    @Override
    public void configure(Map<String, ?> configs,
                          boolean isKey) {
    }

    @Override
    public void close() {
    }

}

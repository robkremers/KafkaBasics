package com.rkremers.kafka.entities;

import java.io.Serializable;
import lombok.Data;

@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 2297854089624764469L;

    private String name;
    private String details;

    public Book() {
        super();
    }

}

package com.rkremers.kafka.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 2297854089624764469L;

    private String name;
    private String details;

    public Book() {
        super();
    }

}

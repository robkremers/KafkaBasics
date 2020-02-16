package com.rkremers.kafka.entities;

import java.io.Serializable;

public class Book implements Serializable {

private static final long serialVersionUID = 2297854089624764469L;

    private String name;
    private String details;

    public Book() {
        super();
    }

    public Book(String name, String details) {
        super();
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book that = (Book) o;

        if (!name.equals(that.name)) return false;
        return details.equals(that.details);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + details.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", topic='" + details + '\'' +
                '}';
    }
}

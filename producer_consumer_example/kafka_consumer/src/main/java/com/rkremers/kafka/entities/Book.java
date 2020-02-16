package com.rkremers.kafka.entities;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 2297854089624764469L;

    private String name;
    private String topic;

    public Book() {
        super();
    }

    public Book(String name, String topic) {
        super();
        this.name = name;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book that = (Book) o;

        if (!name.equals(that.name)) return false;
        return topic.equals(that.topic);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + topic.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}

package com.rkremers.kafka.entities;

import lombok.Data;

@Data
public class Todo {

  private Long id;
  private String summary;
  private String description;

}

package com.jpop.bookservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {

  @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

  String name;

  String author;
}

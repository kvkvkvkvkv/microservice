package com.jpop.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

  @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;

  String name;

  String address;
}

package com.jpop.bookservice.exception;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BookExceptionMessage {
  LocalDate localDate;
  String message;
}

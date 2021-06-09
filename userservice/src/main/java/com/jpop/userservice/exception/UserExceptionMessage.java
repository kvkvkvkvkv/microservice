package com.jpop.userservice.exception;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserExceptionMessage {
  LocalDate localDate;
  String message;
}

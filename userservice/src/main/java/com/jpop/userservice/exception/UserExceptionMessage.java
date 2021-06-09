package com.jpop.userservice.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class UserExceptionMessage {
    LocalDate localDate;
    String message;
}

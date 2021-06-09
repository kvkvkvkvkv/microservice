package com.jpop.bookservice.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class BookException {
    LocalDate localDate;
    String message;
}

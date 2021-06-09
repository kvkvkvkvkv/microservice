package com.jpop.bookservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import java.time.LocalDate;

@ControllerAdvice
public class BookExceptionHandler extends ResponseEntityExceptionHandler {

    BookExceptionMessage bookExceptionMessage;

    @Inject
    public BookExceptionHandler(BookExceptionMessage bookExceptionMessage) {
        this.bookExceptionMessage = bookExceptionMessage;
    }

    @ExceptionHandler({BookNotFoundException.class})
    protected ResponseEntity<BookExceptionMessage> handleBookNotFoundException(Exception ex, WebRequest request) {
        bookExceptionMessage.setLocalDate(LocalDate.now());
        bookExceptionMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(bookExceptionMessage,HttpStatus.NOT_FOUND);
    }
}

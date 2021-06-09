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

    BookException bookException;

    @Inject
    public BookExceptionHandler(BookException bookException) {
        this.bookException = bookException;
    }

    @ExceptionHandler({BookNotFoundException.class})
    protected ResponseEntity<BookException> handleBookNotFoundException(Exception ex, WebRequest request) {
        bookException.setLocalDate(LocalDate.now());
        bookException.setMessage(ex.getMessage());
        return new ResponseEntity<>(bookException,HttpStatus.NOT_FOUND);
    }
}

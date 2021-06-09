package com.jpop.userservice.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import java.time.LocalDate;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    UserExceptionMessage userExceptionMessage;

    @Inject
    public UserExceptionHandler(UserExceptionMessage userExceptionMessage) {
        this.userExceptionMessage = userExceptionMessage;
    }

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<UserExceptionMessage> handleUserNotFoundException(Exception ex, WebRequest request) {
        userExceptionMessage.setLocalDate(LocalDate.now());
        userExceptionMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(userExceptionMessage,HttpStatus.NOT_FOUND);
    }
}

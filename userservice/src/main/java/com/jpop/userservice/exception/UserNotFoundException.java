package com.jpop.userservice.exception;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

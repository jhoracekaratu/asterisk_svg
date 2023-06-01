package com.javaguidance.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String message) {
        super(message);
        log.error("ResourceNotFoundException::constructor  :{}",message);


    }
}

package com.javaguidance.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostNotFoundException extends Exception{
    public PostNotFoundException(String message) {
        super(message);
        log.error("PostNotFoundException::constructor  :{}",message);


    }
}

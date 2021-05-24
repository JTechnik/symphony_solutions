package com.assignment.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SendRequestException extends RuntimeException {

    public SendRequestException(String msg) {
        super(msg);
    }

}

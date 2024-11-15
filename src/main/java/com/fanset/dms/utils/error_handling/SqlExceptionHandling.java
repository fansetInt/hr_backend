package com.fanset.dms.utils.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SqlExceptionHandling extends RuntimeException{
    public SqlExceptionHandling(String message) {
        super(message);
    }
}

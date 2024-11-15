package com.fanset.dms.utils.error_handling;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int status;

}


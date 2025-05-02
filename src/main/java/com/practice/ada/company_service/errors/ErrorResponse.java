package com.practice.ada.company_service.errors;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;
    private int httpStatus;

    ErrorResponse(String code, String message, int httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}

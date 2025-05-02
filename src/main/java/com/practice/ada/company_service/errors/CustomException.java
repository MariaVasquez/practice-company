package com.practice.ada.company_service.errors;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.practice.ada.company_service.util.ResponseCode;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomException extends RuntimeException{

    private String code;
    private String message;
    private int httpStatus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldCustomErrors;

    public CustomException(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message =  responseCode.getMessage();
        this.httpStatus = responseCode.getHttpStatus();
        this.fieldCustomErrors = null;
    }

    public CustomException(String code, String message, int httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public CustomException(String code, String message, int httpStatus, List<FieldError> fieldCustomErrors) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.fieldCustomErrors = fieldCustomErrors;
    }
}

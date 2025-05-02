package com.practice.ada.company_service.dtos;

import com.practice.ada.company_service.errors.FieldError;
import com.practice.ada.company_service.util.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
public class GenericResponseDto<T> {
    private String responseCode;
    private int status;
    private String responseMessage;
    private T data;
    private List<FieldError> fieldErrors;

    public GenericResponseDto(T data){
        this.status = ResponseCode.TRANSACTION_SUCCESS.getHttpStatus();
        this.responseMessage = ResponseCode.TRANSACTION_SUCCESS.getMessage();
        this.data = data;
    }

    public GenericResponseDto(ResponseCode responseCode, String responseMessage, T data) {
        this.responseCode = responseCode.toString();
        this.status = responseCode.getHttpStatus();
        this.responseMessage = responseCode.getMessage();
        this.data = data;
        this.fieldErrors = new ArrayList<>();
    }

    public GenericResponseDto(ResponseCode responseCode, String responseMessage, List<FieldError> fieldErrors) {
        this.status = responseCode.getHttpStatus();
        this.responseCode = responseCode.toString();
        this.responseMessage = responseMessage;
        this.fieldErrors = fieldErrors;
    }
}

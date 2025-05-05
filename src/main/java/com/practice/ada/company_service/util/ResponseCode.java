package com.practice.ada.company_service.util;

import lombok.Getter;

@Getter
public enum ResponseCode {

    DATABASE_ERROR("TRAPP_ERR_1",500,"Database error"),
    COMPANY_NOT_FOUND("TRAPP_ERR_2",404,"Company not found"),
    COMPANY_EXIST("TRAPP_ERR_3",404,"Company is existing"),
    TRANSACTION_SUCCESS("TRAPP_SUCC_4",200, "Ok"),
    UNEXPECTED_ERROR("TRAPP_ERR_5",500,"Unexpected error"),
    APPLICATION_NOT_FOUND("TRAPP_ERR_6",404,"Application not found"),
    APPLICATION_EXIST("TRAPP_ERR_7",404,"Application is existing"),
    VERSION_NOT_FOUND("TRAPP_ERR_8",404,"Version not found"),
    VERSION_EXIST("TRAPP_ERR_9",404,"Version is existing"),;

    private final String code;
    private final int httpStatus;
    private final String message;

    ResponseCode(String code,int status, String htmlMessage ) {
        this.code = code;
        this.httpStatus = status;
        this.message = htmlMessage;
    }

}

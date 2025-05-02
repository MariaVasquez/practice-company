package com.practice.ada.company_service.util;

import lombok.Getter;

@Getter
public enum ResponseCode {

    DATABASE_ERROR("TRAPP_ERR_1",500,"Database error"),
    COMPANY_NOT_FOUND("TRAPP_ERR_2",404,"Company not found"),
    TRANSACTION_SUCCESS("TRAPP_SUCC_3",200, "Ok"),
    UNEXPECTED_ERROR("TRAPP_ERR_4",500,"Unexpected error"),
    ACCOUNT_NOT_FOUND("TRAPP_ERR_2",404,"Account not found"),
    VERSION_NOT_FOUND("TRAPP_ERR_2",404,"Version not found");

    private final String code;
    private final int httpStatus;
    private final String message;

    ResponseCode(String code,int status, String htmlMessage ) {
        this.code = code;
        this.httpStatus = status;
        this.message = htmlMessage;
    }

}

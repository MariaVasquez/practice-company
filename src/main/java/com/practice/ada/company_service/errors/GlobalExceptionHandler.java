package com.practice.ada.company_service.errors;

import com.practice.ada.company_service.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException ex) {
        CustomErrorResponse response = CustomErrorResponse.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .fieldCustomErrors(ex.getFieldCustomErrors())
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldError(err.getField(), err.getDefaultMessage()))
                .toList();

        CustomErrorResponse response = CustomErrorResponse.builder()
                .code("VALIDATION_ERROR")
                .message("Error en los campos enviados")
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .fieldCustomErrors(fieldErrors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGeneralException(Exception ex) {
        log.error("Excepción no controlada:", ex);
        CustomErrorResponse response = CustomErrorResponse.builder()
                .code("INTERNAL_ERROR")
                .message("Ha ocurrido un error inesperado")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}

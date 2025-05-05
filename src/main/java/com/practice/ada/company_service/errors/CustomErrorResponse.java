package com.practice.ada.company_service.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {

    private String code;
    private String message;
    private int httpStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldError> fieldCustomErrors;
}

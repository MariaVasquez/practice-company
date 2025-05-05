package com.practice.ada.company_service.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldError {
    private String field;
    private String error;
}

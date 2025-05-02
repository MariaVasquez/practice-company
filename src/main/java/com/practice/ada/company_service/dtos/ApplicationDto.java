package com.practice.ada.company_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationDto {
    private long appId;
    private String appCode;
    private String appName;
    private String appDescription;
    @JsonIgnore
    private List<VersionDto> versions;
}

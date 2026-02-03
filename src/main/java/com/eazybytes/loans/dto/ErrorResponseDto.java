package com.eazybytes.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private String errorMsg;

    private String errorCode;

    private LocalDateTime errorTime;
}

package com.waa.backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggerEntryDto {
    private long id;
    private String userId;
    private String exceptionMessage;
    private String details;
}

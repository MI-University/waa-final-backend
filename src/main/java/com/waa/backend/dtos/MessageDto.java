package com.waa.backend.dtos;

import com.waa.backend.domains.Property;
import com.waa.backend.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    Long id;
    String message;
    User recipient;
    User sender;
    Property property;
    LocalDate date;
    LocalTime time;
}

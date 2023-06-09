package com.waa.backend.dtos;

import com.waa.backend.domains.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    Long id;
    String message;
    User recipient;
    User sender;
    PropertyDto property;
    LocalDate date;
    LocalTime time;
}

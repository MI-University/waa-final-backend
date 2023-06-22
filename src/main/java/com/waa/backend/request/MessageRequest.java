package com.waa.backend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    Long id;
    String message;
    Long recipientId;
    Long senderId;
    Long propertyId;
}

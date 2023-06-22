package com.waa.backend.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Message extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String message;
    @OneToOne
    @NotNull
    @JsonBackReference(value = "message-recipient")
    User recipient;
    @OneToOne
    @NotNull
    @JsonBackReference(value = "message-sender")
    User sender;
    @ManyToOne
    @JsonBackReference(value = "message-property")
    Property property;
}

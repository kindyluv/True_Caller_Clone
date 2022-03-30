package com.precious.truecaller.data.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsSenderRequest {
    @NotNull(message = "Field can not be null")
    private String mobileNumber;
    @NotNull(message = "Field can not be null")
    private String userName;
    @NotNull(message = "Field can not be null")
    private String message;
}

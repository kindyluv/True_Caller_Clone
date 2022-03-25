package com.precious.truecaller.data.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileNumberRequest {
    @NotNull(message = "Field can not be null")
    private String countryCode;
    @NotNull(message = "Field can not be null")
    private String mobileNumber;
}

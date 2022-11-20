package com.precious.truecaller.data.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Setter
@Getter
public class CallRequest {
    @Size(min = 11, max = 14, message = "Phone number should be 11 numbers")
    @NotNull(message = "please provide a mobile number, this cannot be null")
    private String mobileNumber;
    @Size(min = 1, max = 3, message = "Country code should be minimum of 1 number and maximum of 3 numbers")
    @NotNull(message = "please provide a mobile number, this cannot be null")
    private String countryCode;

    public String getMobileNumber() {
        return countryCode + mobileNumber;
    }
}

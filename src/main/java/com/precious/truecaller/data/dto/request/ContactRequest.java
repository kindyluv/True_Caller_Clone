package com.precious.truecaller.data.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {
    @NotNull(message = "please provide your last name, this field cannot be null")
    private String userName;

    @NotNull(message = "please provide a email, this cannot be null")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Invalid email")
    private String email;

    @Size(min = 11, max = 14, message = "Phone number should be 11 character")
    @NotNull(message = "please provide a mobile number, this cannot be null")
    private String mobileNumber;

    private String countryCode;
    private String companyName;
}

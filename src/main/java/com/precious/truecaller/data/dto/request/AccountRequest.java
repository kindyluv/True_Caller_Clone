package com.precious.truecaller.data.dto.request;

import com.precious.truecaller.data.models.user.UserCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull(message = "pls provide a username")
    private String userName;
    @NotNull(message = "pls provide your email")
    @Email(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Invalid email")
    private String email;
    @NotNull(message = "pls provide a password")
    @Size(min = 8, message = "pls provide a strong password")
    private String password;
    @NotNull(message = "please select a user category")
    private UserCategory userCategory;
}

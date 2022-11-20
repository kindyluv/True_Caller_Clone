package com.precious.truecaller.data.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

//@Entity(name = "address")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String streetName;
    private String streetNumber;
    private String zipCode;
    private String city;
    private String country;
}

package com.precious.truecaller.data.models.mobile;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "phone_number")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String countryCode;
    private String mobileNumber;
}

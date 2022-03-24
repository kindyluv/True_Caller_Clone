package com.precious.truecaller.data.models.call;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "user_call")
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany
    private List<MobileNumber> MobileNumber;
}
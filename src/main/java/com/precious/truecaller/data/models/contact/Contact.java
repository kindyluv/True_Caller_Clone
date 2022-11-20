package com.precious.truecaller.data.models.contact;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userName;
    private String companyName;

    @Column(unique = true)
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    private MobileNumber mobileNumber;

    private Boolean isBlocked = false;

    @CreationTimestamp
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dateCreated;
}

package com.precious.truecaller.data.models.call;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AudioCall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany
    private List<MobileNumber> mobileNumber;
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate callDate;
}

package com.precious.truecaller.data.models.call;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.precious.truecaller.data.models.user.Account;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VideoCall {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Account user;
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate videoCallDate;
}

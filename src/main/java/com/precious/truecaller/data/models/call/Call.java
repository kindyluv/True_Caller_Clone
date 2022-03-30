package com.precious.truecaller.data.models.call;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "callers")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Call {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany
    private List<MobileNumber> mobileNumber;
    @CreationTimestamp
    private LocalDate dateCallWasMade;
}

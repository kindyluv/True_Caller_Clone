package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
class MobileNumberRepositoryTest {

    private final MobileNumberRepository mobileNumberRepository;

    @Autowired
    MobileNumberRepositoryTest(MobileNumberRepository mobileNumberRepository) {
        this.mobileNumberRepository = mobileNumberRepository;
    }

    private MobileNumber mobileNumber;

    @BeforeEach
    void setUp() {
        mobileNumber = mobileNumberRepository.save(MobileNumber.builder()
                .countryCode("+234")
                .number("09032354678")
                .isBlocked(false)
                .build());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByMobileNumber() {
        assertThat(mobileNumber).isNotNull();
        assertThat(mobileNumber.getNumber()).isEqualTo("09032354678");
    }
}
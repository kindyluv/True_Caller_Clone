package com.precious.truecaller.service.mobileService;

import com.precious.truecaller.data.dto.request.MobileNumberRequest;
import com.precious.truecaller.data.dto.response.MobileNumberResponse;
import com.precious.truecaller.data.models.mobile.MobileNumber;
import com.precious.truecaller.data.repositories.ContactRepository;
import com.precious.truecaller.data.repositories.MobileNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
class MobileNumberServiceTest {

    private final MobileNumberService mobileNumberService;

    @Autowired
    MobileNumberServiceTest(MobileNumberService mobileNumberService) {
        this.mobileNumberService = mobileNumberService;
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
//        mobileNumberService.deleteAllMobileNumber();
    }

    @Test
    void addMobileNumber() {
      MobileNumberRequest mobileNumberRequest = MobileNumberRequest.builder()
               .countryCode("+234")
                .number("09032456789")
                .build();

       MobileNumberResponse mobileNumberResponse = mobileNumberService.addMobileNumber(mobileNumberRequest);
       assertThat(mobileNumberResponse).isNotNull();
        assertThat(mobileNumberResponse.getNumber()).isEqualTo(mobileNumberRequest.getNumber());

    }

    @Test
    void blockedContactByMobileNumber() {
        MobileNumberRequest mobileNumberRequest = MobileNumberRequest.builder()
                .countryCode("+234")
                .number("09039856767")
                .build();

        MobileNumberResponse mobileNumberResponse = mobileNumberService.addMobileNumber(mobileNumberRequest);
        assertThat(mobileNumberResponse).isNotNull();
        assertThat(mobileNumberResponse.getNumber()).isEqualTo(mobileNumberRequest.getNumber());

        assertThat(mobileNumberResponse.getIsBlocked()).isFalse();
        Boolean number = mobileNumberService.blockedContactByMobileNumber(mobileNumberResponse.getNumber());
        assertThat(number).isTrue();
    }

    @Test
    void unBlockedMobileNumberByMobileNumber() {
        MobileNumberRequest mobileNumberRequest = MobileNumberRequest.builder()
                .countryCode("+234")
                .number("09032463767")
                .build();

        MobileNumberResponse mobileNumberResponse = mobileNumberService.addMobileNumber(mobileNumberRequest);
        assertThat(mobileNumberResponse).isNotNull();
        assertThat(mobileNumberResponse.getNumber()).isEqualTo(mobileNumberRequest.getNumber());

        assertThat(mobileNumberResponse.getIsBlocked()).isFalse();
        Boolean number = mobileNumberService.blockedContactByMobileNumber(mobileNumberResponse.getNumber());
        assertThat(number).isTrue();

        Boolean unBlocked = mobileNumberService.unBlockedMobileNumberByMobileNumber(mobileNumberResponse.getNumber());
        assertThat(unBlocked).isFalse();
    }

    @Test
    void findAllBlockedMobileNumbers() {
        MobileNumberRequest mobileNumberRequest = MobileNumberRequest.builder()
                .countryCode("+234")
                .number("07032456767")
                .build();

        MobileNumberResponse mobileNumberResponse = mobileNumberService.addMobileNumber(mobileNumberRequest);
        assertThat(mobileNumberResponse).isNotNull();
        assertThat(mobileNumberResponse.getNumber()).isEqualTo(mobileNumberRequest.getNumber());

        assertThat(mobileNumberResponse.getIsBlocked()).isFalse();
        Boolean number = mobileNumberService.blockedContactByMobileNumber(mobileNumberResponse.getNumber());
        assertThat(number).isTrue();

        List<MobileNumber> numb = mobileNumberService.findAllBlockedMobileNumbers();
        assertThat(numb.size()).isEqualTo(1);
    }
}
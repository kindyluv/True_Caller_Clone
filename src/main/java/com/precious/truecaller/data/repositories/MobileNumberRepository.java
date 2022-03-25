package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.mobile.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Integer> {
    Optional<MobileNumber> findByMobileNumber(String mobileNumber);
}

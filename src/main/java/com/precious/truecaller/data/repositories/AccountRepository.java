package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}

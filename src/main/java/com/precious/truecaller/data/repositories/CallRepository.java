package com.precious.truecaller.data.repositories;

import com.precious.truecaller.data.models.call.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {
}

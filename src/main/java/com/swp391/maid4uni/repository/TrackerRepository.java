package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Account;
import com.swp391.maid4uni.entity.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackerRepository extends JpaRepository<Tracker,Integer> {
    Tracker findByAccount(Account staff);
}

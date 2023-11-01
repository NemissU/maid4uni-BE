package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findTop5ByOrderByStarDesc();
}

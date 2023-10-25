package com.swp391.maid4uni.repository;

import com.swp391.maid4uni.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}

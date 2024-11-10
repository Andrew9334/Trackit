package com.svprofi.trackit.repository;

import com.svprofi.trackit.model.PostMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMovementRepository extends JpaRepository<PostMovement, Long> {
    List<PostMovement> findByPostItemId(Long postItemId);
}

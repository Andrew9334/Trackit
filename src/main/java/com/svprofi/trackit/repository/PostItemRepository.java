package com.svprofi.trackit.repository;

import com.svprofi.trackit.model.PostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostItemRepository extends JpaRepository<PostItem, Long> {
}

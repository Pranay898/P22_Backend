package com.pinterest.clone.repository;

import com.pinterest.clone.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {
    List<Pin> findByUserId(Long userId);

    List<Pin> findByBoardId(Long boardId);

    List<Pin> findByTitleContainingIgnoreCase(String title);
}

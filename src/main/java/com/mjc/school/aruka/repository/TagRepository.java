package com.mjc.school.aruka.repository;

import com.mjc.school.aruka.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
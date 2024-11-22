package com.mjc.school.aruka.repository;

import com.mjc.school.aruka.model.AuthorNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorNewsRepository extends JpaRepository<AuthorNews, Long> {
}
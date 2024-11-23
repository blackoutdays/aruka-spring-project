package com.mjc.school.aruka.repository;

import com.mjc.school.aruka.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n " +
            "LEFT JOIN n.author a " +
            "LEFT JOIN n.tags t " +
            "WHERE (:authorName IS NULL OR a.firstName LIKE %:authorName% OR a.lastName LIKE %:authorName%) " +
            "AND (:title IS NULL OR n.title LIKE %:title%) " +
            "AND (:content IS NULL OR n.content LIKE %:content%) " +
            "AND (:tagNames IS NULL OR t.name IN :tagNames) " +
            "GROUP BY n.id")
    Page<News> searchNews(@Param("authorName") String authorName,
                          @Param("title") String title,
                          @Param("content") String content,
                          @Param("tagNames") List<String> tagNames,
                          Pageable pageable);
}
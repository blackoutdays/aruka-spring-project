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

    @Query("SELECT DISTINCT n FROM News n " +
            "LEFT JOIN n.tags t " +
            "WHERE " +
            "(:authorName IS NULL OR LOWER(n.author.firstName) LIKE LOWER(CONCAT('%', :authorName, '%')) OR LOWER(n.author.lastName) LIKE LOWER(CONCAT('%', :authorName, '%'))) AND " +
            "(:title IS NULL OR LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:content IS NULL OR LOWER(n.content) LIKE LOWER(CONCAT('%', :content, '%'))) AND " +
            "(:tagIds IS NULL OR t.id IN :tagIds)")
    Page<News> searchNews(
            @Param("authorName") String authorName,
            @Param("title") String title,
            @Param("content") String content,
            @Param("tagIds") List<Long> tagIds,
            Pageable pageable
    );
}
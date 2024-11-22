package com.mjc.school.aruka.repository;

import com.mjc.school.aruka.model.NewsTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTagRepository extends JpaRepository<NewsTag, Long> {
    boolean existsByNewsIdAndTagId(Long newsId, Long tagId);

    @Query("SELECT nt FROM NewsTag nt WHERE " +
            "(:newsId IS NULL OR nt.news.id = :newsId) AND " +
            "(:tagId IS NULL OR nt.tag.id = :tagId)")
    Page<NewsTag> findAllByFilters(@Param("newsId") Long newsId,
                                   @Param("tagId") Long tagId,
                                   Pageable pageable);
}
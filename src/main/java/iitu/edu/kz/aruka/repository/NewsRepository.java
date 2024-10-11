package iitu.edu.kz.aruka.repository;

import iitu.edu.kz.aruka.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
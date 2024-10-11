package iitu.edu.kz.aruka.repository;

import iitu.edu.kz.aruka.model.AuthorNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorNewsRepository extends JpaRepository<AuthorNews, Long> {
}
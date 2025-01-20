package com.ensem.gestiondoctorants.repository;

import com.ensem.gestiondoctorants.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {}

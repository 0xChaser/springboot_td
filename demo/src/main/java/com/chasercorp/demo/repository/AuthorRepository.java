package com.chasercorp.demo.repository;

import com.chasercorp.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, String> {
} 
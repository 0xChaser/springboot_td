package com.chasercorp.demo.repository;

import com.chasercorp.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
} 
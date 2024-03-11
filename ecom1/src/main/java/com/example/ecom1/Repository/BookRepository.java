package com.example.ecom1.Repository;

import com.example.ecom1.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
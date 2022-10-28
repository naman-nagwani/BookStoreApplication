package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.entity.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookData,Integer> {
}

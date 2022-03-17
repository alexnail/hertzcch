package com.alexnail.hertzcch.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexnail.hertzcch.entities.Book;
import com.alexnail.hertzcch.entities.BookId;
import com.alexnail.hertzcch.entities.User;

public interface BookRepository extends JpaRepository<Book, BookId> {

    List<Book> findByLoanedBy(User user);
}

package com.alexnail.hertzcch.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.alexnail.hertzcch.models.BookModel;

@Service
public class BookService {

    public Collection<BookModel> findAll() {
        return null;
    }

    public BookModel addBook(BookModel book) {
        return null;
    }

    public BookModel removeBook(BookModel book) {
        return null;
    }

    public BookModel loanBook(BookModel book, Long userId) {
        return null;
    }

    public BookModel returnBook(BookModel book, Long userId) {
        return null;
    }
}

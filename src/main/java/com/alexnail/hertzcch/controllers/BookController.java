package com.alexnail.hertzcch.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alexnail.hertzcch.models.BookModel;
import com.alexnail.hertzcch.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public Collection<BookModel> getBooks() {
        return bookService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookModel addBook(@RequestBody BookModel book) {
        return bookService.addBook(book);
    }

    @DeleteMapping
    public BookModel removeBook(@RequestBody BookModel book) {
        return bookService.removeBook(book);
    }

    @PutMapping(value = "/{userId}")
    public BookModel loanBook(@RequestBody BookModel book, @PathVariable Long userId) {
        return bookService.loanBook(book, userId);
    }

    @DeleteMapping(value = "/{userId}")
    public BookModel returnBook(@RequestBody BookModel book, @PathVariable Long userId) {
        return bookService.returnBook(book, userId);
    }
}

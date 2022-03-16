package com.alexnail.hertzcch.controllers;

import java.util.Collection;

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

@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping
    public Collection<BookModel> getBooks() {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookModel addBook(@RequestBody BookModel book) {
        return book;
    }

    @DeleteMapping
    public BookModel removeBook(@RequestBody BookModel book) {
        return book;
    }

    @PutMapping(value = "/{userId}")
    public BookModel loanBook(@RequestBody BookModel book, @PathVariable Long userId) {
        return book;
    }

    @DeleteMapping(value = "/{userId}")
    public BookModel removeBook(@RequestBody BookModel book, @PathVariable Long userId) {
        return book;
    }
}

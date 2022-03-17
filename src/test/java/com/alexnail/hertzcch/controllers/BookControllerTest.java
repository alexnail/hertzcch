package com.alexnail.hertzcch.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alexnail.hertzcch.models.BookModel;
import com.alexnail.hertzcch.services.BookService;

@WebMvcTest
public class BookControllerTest {

    private static final String BOOK = "{\"title\":\"Book with Title\", \"author\": \"Very Famous Author\", \"categories\":[]}";

    private static final BookModel BOOK_MODEL = new BookModel("Book with Title", "Very Famous Author", List.of());

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/books"))
           .andExpect(status().isOk());
    }

    @Test
    void testAddBook() throws Exception {
        given(bookService.addBook(any(BookModel.class))).willReturn(BOOK_MODEL);

        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.title", is(BOOK_MODEL.getTitle())))
           .andExpect(jsonPath("$.author", is(BOOK_MODEL.getAuthor())))
           .andExpect(jsonPath("$.categories", is(BOOK_MODEL.getCategories())));
    }

    @Test
    void testRemoveBook() throws Exception {
        given(bookService.removeBook(any(BookModel.class))).willReturn(BOOK_MODEL);

        mvc.perform(MockMvcRequestBuilders.delete("/api/books")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is(BOOK_MODEL.getTitle())))
           .andExpect(jsonPath("$.author", is(BOOK_MODEL.getAuthor())))
           .andExpect(jsonPath("$.categories", is(BOOK_MODEL.getCategories())));
    }

    @Test
    void testLoanBook() throws Exception {
        given(bookService.loanBook(any(BookModel.class), anyLong())).willReturn(BOOK_MODEL);

        mvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is(BOOK_MODEL.getTitle())))
           .andExpect(jsonPath("$.author", is(BOOK_MODEL.getAuthor())))
           .andExpect(jsonPath("$.categories", is(BOOK_MODEL.getCategories())));
    }

    @Test
    void testReturnBook() throws Exception {
        given(bookService.returnBook(any(BookModel.class), anyLong())).willReturn(BOOK_MODEL);

        mvc.perform(MockMvcRequestBuilders.delete("/api/books/1")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is(BOOK_MODEL.getTitle())))
           .andExpect(jsonPath("$.author", is(BOOK_MODEL.getAuthor())))
           .andExpect(jsonPath("$.categories", is(BOOK_MODEL.getCategories())));
    }
}

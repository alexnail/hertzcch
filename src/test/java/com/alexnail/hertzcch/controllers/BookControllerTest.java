package com.alexnail.hertzcch.controllers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
public class BookControllerTest {

    public static final String BOOK = "{\"title\":\"Book with Title\", \"author\": \"Very Famous Author\", \"categories\":[]}";

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/books"))
           .andExpect(status().isOk());
    }

    @Test
    void testAddBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$.title", is("Book with Title")))
           .andExpect(jsonPath("$.author", is("Very Famous Author")))
           .andExpect(jsonPath("$.categories", is(List.of())));

    }

    @Test
    void testRemoveBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/books")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is("Book with Title")))
           .andExpect(jsonPath("$.author", is("Very Famous Author")))
           .andExpect(jsonPath("$.categories", is(List.of())));
    }

    @Test
    void testLoanBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is("Book with Title")))
           .andExpect(jsonPath("$.author", is("Very Famous Author")))
           .andExpect(jsonPath("$.categories", is(List.of())));
    }

    @Test
    void testReturnBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/books/1")
                                          .content(BOOK)
                                          .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.title", is("Book with Title")))
           .andExpect(jsonPath("$.author", is("Very Famous Author")))
           .andExpect(jsonPath("$.categories", is(List.of())));
    }
}

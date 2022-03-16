package com.alexnail.hertzcch.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/books"))
           .andExpect(status().isOk());
    }

    @Test
    void testAddBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/books"))
           .andExpect(status().isCreated())
           .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testRemoveBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/books"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testLoanBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/api/books/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testReturnBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$", hasSize(1)));
    }
}

package com.alexnail.hertzcch.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alexnail.hertzcch.models.BookModel;

@SpringBootTest
class BookServiceTest {

    public static final long USER_ID = 1L;

    @Autowired
    private BookService service;

    @Test
    void testAddBook_Success() {
        BookModel bookModel = service.addBook(new BookModel("Title", "Author", List.of()));
        assertAll(() -> assertNotNull(bookModel),
                  () ->assertEquals("Title", bookModel.getTitle()),
                  () ->assertEquals("Author", bookModel.getAuthor()),
                  () ->assertEquals(List.of(), bookModel.getCategories())
        );
    }

    @Test
    void testCantAddTheSameBookMoreThanOnce() {
        BookModel book = new BookModel("Title", "Author", List.of());
        BookModel addedBook = service.addBook(book);
        assertEquals(book, addedBook);

        Exception exception = assertThrows(Exception.class, () -> service.addBook(book));
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    void testLoanTheSameBookTwice() {
        BookModel loaned = service.loanBook(new BookModel("Title", "Author", List.of()), USER_ID);
        Exception exception = assertThrows(Exception.class, () -> service.loanBook(new BookModel("Title", "Author", List.of()), USER_ID));
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    void testLoanBook_noOutstandingLoanedBooks() {
        BookModel loaned = service.loanBook(new BookModel("Title", "Author", List.of()), USER_ID);
        BookModel loaned2 = service.loanBook(new BookModel("Another Title", "Author", List.of()), USER_ID);
        BookModel loaned3 = service.loanBook(new BookModel("Title", "Another Author", List.of()), USER_ID);
        assertAll(() -> assertNotNull(loaned),
                  () -> assertNotNull(loaned2),
                  () -> assertNotNull(loaned3)
        );
    }

    @Test
    void testLoanBook_outstandingLoanedBooks() {
        BookModel loaned = service.loanBook(new BookModel("Title", "Author", List.of()), USER_ID);
        BookModel loaned2 = service.loanBook(new BookModel("Another Title", "Author", List.of()), USER_ID);
        BookModel loaned3 = service.loanBook(new BookModel("Title", "Another Author", List.of()), USER_ID);
        Exception exception = assertThrows(Exception.class,
                                           () -> service.loanBook(new BookModel("Another Title", "Another Author", List.of()), USER_ID));
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    void testReturnBook() {
        BookModel loaned = service.loanBook(new BookModel("Title", "Author", List.of()), USER_ID);
        BookModel returned = service.returnBook(loaned, USER_ID);
        assertAll(() -> assertNotNull(loaned),
                  () -> assertNotNull(returned),
                  () -> assertEquals(loaned, returned)
        );
    }

    @Test
    void testReturnBookThatWasntLoaned() {
        Exception exception = assertThrows(Exception.class, () -> service.returnBook(new BookModel("Title", "Author", List.of()), USER_ID));
        assertEquals("Exception message", exception.getMessage());
    }
}
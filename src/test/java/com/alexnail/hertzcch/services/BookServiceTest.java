package com.alexnail.hertzcch.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alexnail.hertzcch.exceptions.LoanBookException;
import com.alexnail.hertzcch.models.BookModel;

@SpringBootTest
class BookServiceTest {

    private static final long USER_ID = 1L;
    private static final long ANOTHER_USER_ID = 2L;

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
    void testRemove() {
        BookModel book = new BookModel("Title", "Author", List.of());
        BookModel deleted = service.removeBook(book);
        assertEquals(book, deleted);
    }

    @Test
    void testLoanTheSameBookTwice() {
        BookModel book = new BookModel("Title", "Author", List.of());
        service.addBook(book);

        BookModel loaned = service.loanBook(book, USER_ID);

        Exception exception = assertThrows(LoanBookException.class, () -> service.loanBook(book, USER_ID));
        assertEquals("The [Title] book has been already loaned by another user", exception.getMessage());
    }

    @Test
    void testLoanBook_noOutstandingLoanedBooks() {
        BookModel book1 = new BookModel("Title", "Author", List.of());
        BookModel book2 = new BookModel("Another Title", "Author", List.of());
        BookModel book3 = new BookModel("Title", "Another Author", List.of());
        service.addBooks(book1, book2, book3);

        BookModel loaned1 = service.loanBook(book1, USER_ID);
        BookModel loaned2 = service.loanBook(book2, USER_ID);
        BookModel loaned3 = service.loanBook(book3, USER_ID);
        assertAll(() -> assertNotNull(loaned1),
                  () -> assertNotNull(loaned2),
                  () -> assertNotNull(loaned3)
        );
    }

    @Test
    void testLoanBook_outstandingLoanedBooks() {
        BookModel book1 = new BookModel("Title", "Author", List.of());
        BookModel book2 = new BookModel("Another Title", "Author", List.of());
        BookModel book3 = new BookModel("Title", "Another Author", List.of());
        BookModel book4 = new BookModel("Another Title", "Another Author", List.of());

        service.addBooks(book1, book2, book3, book4);

        BookModel loaned = service.loanBook(book1, USER_ID);
        BookModel loaned2 = service.loanBook(book2, USER_ID);
        BookModel loaned3 = service.loanBook(book3, USER_ID);
        Exception exception = assertThrows(LoanBookException.class, () -> service.loanBook(book4, USER_ID));
        assertEquals("The user has already 3 books loaned.", exception.getMessage());
    }

    @Test
    void testReturnBook() {
        BookModel book1 = new BookModel("Title", "Author", List.of());
        service.addBooks(book1);

        BookModel loaned = service.loanBook(book1, USER_ID);
        BookModel returned = service.returnBook(loaned, USER_ID);
        assertAll(() -> assertNotNull(loaned),
                  () -> assertNotNull(returned),
                  () -> assertEquals(loaned, returned)
        );
    }

    @Test
    void testReturnBookThatWasntLoaned() {
        assertThrows(EntityNotFoundException.class, () -> service.returnBook(new BookModel("XYZ", "Author", List.of()), USER_ID));
    }

    @Test
    void testReturnBookByLoanedAnotherUser() {
        BookModel book1 = new BookModel("Title", "Author", List.of());
        service.addBooks(book1);

        BookModel loaned = service.loanBook(book1, USER_ID);
        Exception exception = assertThrows(LoanBookException.class, () -> service.returnBook(loaned, ANOTHER_USER_ID));
        assertEquals("An attempt to return a book loaned by different user has been made", exception.getMessage());
    }
}
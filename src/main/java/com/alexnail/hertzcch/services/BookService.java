package com.alexnail.hertzcch.services;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexnail.hertzcch.entities.Book;
import com.alexnail.hertzcch.entities.User;
import com.alexnail.hertzcch.exceptions.LoanBookException;
import com.alexnail.hertzcch.mappers.BookMapper;
import com.alexnail.hertzcch.models.BookModel;
import com.alexnail.hertzcch.repositories.BookRepository;
import com.alexnail.hertzcch.repositories.UserRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookMapper bookMapper;

    public Collection<BookModel> findAll() {
        return bookMapper.entitiesToModels(bookRepository.findAll());
    }

    public BookModel addBook(BookModel book) {
        Book saved = bookRepository.save(bookMapper.toEntity(book));
        return bookMapper.toModel(saved);
    }

    public Collection<BookModel> addBooks(BookModel ...bookModels) {
        List<Book> books = bookRepository.saveAll(bookMapper.modelsToEntities(List.of(bookModels)));
        return bookMapper.entitiesToModels(books);
    }

    public BookModel removeBook(BookModel book) {
        bookRepository.delete(bookMapper.toEntity(book));
        return book;
    }

    public BookModel loanBook(BookModel bookModel, Long userId) throws LoanBookException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Book book = bookRepository.findById(bookMapper.toBookId(bookModel)).orElseThrow(EntityNotFoundException::new);
        if (book.getLoanedBy() != null) {
            throw new LoanBookException(String.format("The [%s] book has been already loaned by another user", book.getTitle()));
        }
        List<Book> loanedByUserList = bookRepository.findByLoanedBy(user);
        if (loanedByUserList.size() >= 3 ) {
            throw new LoanBookException("The user has already 3 books loaned.");
        }
        book.setLoanedBy(user);
        return bookMapper.toModel(bookRepository.save(book));
    }

    public BookModel returnBook(BookModel bookModel, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Book book = bookRepository.findById(bookMapper.toBookId(bookModel)).orElseThrow(EntityNotFoundException::new);

        if (!book.getLoanedBy().getId().equals(user.getId())) {
            throw new LoanBookException("An attempt to return a book loaned by different user has been made");
        }

        book.setLoanedBy(null);
        return bookMapper.toModel(bookRepository.save(book));
    }
}

package com.bookService.service;

import com.bookService.domain.Book;
import com.bookService.repository.BookRepository;
import com.bookService.exceptions.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book){
        log.debug("Saving book: {}", book);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        log.debug("Getting all Books");
        return List.copyOf(bookRepository.findAll());
    }

    public Book getBook(long id){
        log.debug("Getting Book, id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found, id: " + id));
    }

    public Book updateBook(long id, Book book){
        log.debug("Updating book: {}", book);
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found, id: " + id));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setStock(book.getStock());
        bookRepository.save(existingBook);
        return book;
    }

    public void deleteBook(long id) {
        log.debug("Deleting book, id: {}", id);
        var book = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found, cannot delete, id: " + id));
        bookRepository.delete(book);
    }
}

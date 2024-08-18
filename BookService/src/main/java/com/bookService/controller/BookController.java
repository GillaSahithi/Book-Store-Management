package com.bookService.controller;

import com.bookService.converter.BookDtoConverter;
import com.bookService.domain.Book;
import com.bookService.dto.BookDto;
import com.bookService.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookController {

    private final BookService bookService;
    private  final BookDtoConverter converter;

    public BookController(BookService bookService, BookDtoConverter converter) {
        this.bookService = bookService;
        this.converter = converter;
    }

    @PostMapping("/book")
    public ResponseEntity<BookDto> saveBook(@Valid @RequestBody BookDto dto) {
        Book book = converter.toEntity(dto);
        book = bookService.saveBook(book);
        var responseBody = converter.toDto(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        var books = bookService.getAllBooks();
        if(books.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(converter.toDTOs(books));
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        var book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Patient deleted");
    }
}

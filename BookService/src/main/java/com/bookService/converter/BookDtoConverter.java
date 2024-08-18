package com.bookService.converter;

import com.bookService.domain.Book;
import com.bookService.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDtoConverter {
    public static List<BookDto> toDTOs(List<Book> books){
        return books.stream().map(BookDtoConverter::fromEntity).toList();
    }

    public static BookDto fromEntity(Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getStock()
        );
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(),
                book.getAuthor(), book.getPrice(), book.getStock());
    }

    public Book toEntity(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPrice(dto.price());
        book.setStock(dto.stock());
        return book;
    }
}

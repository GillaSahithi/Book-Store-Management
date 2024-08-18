package com.orderService.feign_clients;

import com.orderService.dto.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "book-service", url = "http://localhost:8100/api/v1")
public interface BookClient {
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Long id);

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book);
}

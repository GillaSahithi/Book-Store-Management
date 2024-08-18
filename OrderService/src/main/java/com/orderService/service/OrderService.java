package com.orderService.service;

import com.orderService.domain.Order;
import com.orderService.dto.Book;
import com.orderService.exceptions.OrderNotFoundException;
import com.orderService.feign_clients.BookClient;
import com.orderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookClient bookClient;

    public Order saveOrder(Order order){
        log.debug("Placing Order {}", order);
        Book book = bookClient.getBook(order.getBookId());
        if (book == null || book.getStock() < order.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }
        // Reduce stock and save order
        book.setStock(book.getStock() - order.getQuantity());
        // Assuming a method to update book stock
        bookClient.updateBook(book.getId(), book);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        log.debug("Getting all Orders");
        return List.copyOf(orderRepository.findAll());
    }

    public Order getOrder(long id){
        log.debug("Getting Order, id: {}", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found, id: " + id));
    }

    public Order updateOrder(long id, Order order){
        log.debug("Updating Order: {}", order);
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found, id: " + id));
        existingOrder.setCustomerId(order.getCustomerId());
        existingOrder.setBookId(order.getBookId());
        existingOrder.setQuantity(order.getQuantity());
        orderRepository.save(existingOrder);
        return order;
    }

    public void deleteOrder(long id) {
        log.debug("Deleting Order, id: {}", id);
        var order = orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found, cannot delete, id: " + id));
        orderRepository.delete(order);
    }
}

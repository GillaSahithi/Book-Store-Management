package com.orderService.controller;

import com.orderService.converter.OrderDtoConverter;
import com.orderService.domain.Order;
import com.orderService.dto.OrderDto;
import com.orderService.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;
    private  final OrderDtoConverter converter;

    public OrderController(OrderService orderService, OrderDtoConverter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDto> saveOrder(@Valid @RequestBody OrderDto dto) {
        Order order = converter.toEntity(dto);
        order = orderService.saveOrder(order);
        var responseBody = converter.toDto(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        var orders = orderService.getAllOrders();
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(converter.toDTOs(orders));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        var order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted");
    }
}


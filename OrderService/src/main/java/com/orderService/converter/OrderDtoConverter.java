package com.orderService.converter;

import com.orderService.domain.Order;
import com.orderService.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoConverter {
    public OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomerId(),
                order.getBookId(),
                order.getQuantity(),
                order.getStatus()
        );
    }

    public Order toEntity(OrderDto orderDto) {
        return new Order(
                orderDto.id(),
                orderDto.customerId(),
                orderDto.bookId(),
                orderDto.quantity(),
                orderDto.status()
        );
    }
}

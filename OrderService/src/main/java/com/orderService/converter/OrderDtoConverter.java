package com.orderService.converter;

import com.orderService.domain.Order;
import com.orderService.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDtoConverter {
    public static List<OrderDto> toDTOs(List<Order> books){
        return books.stream().map(OrderDtoConverter::fromEntity).toList();
    }

    public static OrderDto fromEntity(Order order){
        return new OrderDto(
                order.getId(),
                order.getCustomerId(),
                order.getBookId(),
                order.getQuantity(),
                order.getStatus()
        );
    }
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
        Order order = new Order();
        order.setCustomerId(orderDto.customerId());
        order.setBookId(orderDto.bookId());
        order.setQuantity(orderDto.quantity());
        order.setStatus(orderDto.status());
        return order;
    }
}

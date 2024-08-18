package com.customerService.converter;

import com.customerService.domain.Customer;
import com.customerService.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDtoConverter {
    public static List<CustomerDto> toDTOs(List<Customer> customers){
        return customers.stream().map(CustomerDtoConverter::fromEntity).toList();
    }

    public static CustomerDto fromEntity(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(),
                customer.getEmail(), customer.getPhoneNumber());
    }

    public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setPhoneNumber(dto.phoneNumber());
        return customer;
    }
}

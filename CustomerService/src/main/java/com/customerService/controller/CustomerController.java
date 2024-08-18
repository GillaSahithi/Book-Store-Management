package com.customerService.controller;

import com.customerService.converter.CustomerDtoConverter;
import com.customerService.service.CustomerService;
import com.customerService.domain.Customer;
import com.customerService.dto.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;
    private  final CustomerDtoConverter converter;

    public CustomerController(CustomerService customerService, CustomerDtoConverter converter) {
        this.customerService = customerService;
        this.converter = converter;
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto dto) {
        Customer customer = converter.toEntity(dto);
        customer = customerService.saveCustomer(customer);
        var responseBody = converter.toDto(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        var customers = customerService.getAllCustomers();
        if(customers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(converter.toDTOs(customers));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        var customer = customerService.getCustomer(id);
        return ResponseEntity.ok(converter.toDto(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(converter.toDto(updatedCustomer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted");
    }
}

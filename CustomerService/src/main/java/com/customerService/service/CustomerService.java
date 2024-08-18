package com.customerService.service;

import com.customerService.domain.Customer;
import com.customerService.exceptions.CustomerNotFoundException;
import com.customerService.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){
        log.debug("Creating Customer {}", customer);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        log.debug("Getting all Customers");
        return List.copyOf(customerRepository.findAll());
    }

    public Customer getCustomer(long id){
        log.debug("Getting Customer, id: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found, id: " + id));
    }

    public Customer updateCustomer(long id, Customer customer){
        log.debug("Updating Customer: {}", customer);
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found, id: " + id));
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(existingCustomer);
        return customer;
    }

    public void deleteCustomer(long id) {
        log.debug("Deleting Customer, id: {}", id);
        var customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found, cannot delete, id: " + id));
        customerRepository.delete(customer);
    }
}

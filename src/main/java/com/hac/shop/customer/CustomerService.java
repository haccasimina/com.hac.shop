package com.hac.shop.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private  final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public void addNewStudent(Customer customer) {
        Optional<Customer> customerByEmail =   customerRepository.findCustomerByEmail(customer.getEmail());

        if(customerByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        customerRepository.save(customer);
    }

    public void deleteStudent(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if(!exists){
            throw  new IllegalStateException("customer with id "+customerId+ " does not exists");
        }

        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateStudent(Long customerId, String name, String email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new IllegalStateException(
                        "student with id "+customerId+ " does not exists"
                ));

        if( name != null &&
                name.length() > 0 &&
                    !Objects.equals(customer.getName(), name)) {
            customer.setName(name);
        }

        if( email != null &&
                email.length() > 0 &&
                !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if(customerOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            customer.setEmail(email);
        }

    }
}

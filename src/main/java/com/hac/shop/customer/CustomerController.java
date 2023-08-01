package com.hac.shop.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final  CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();

    }

    @PostMapping
    public void registerNewCustomer(@RequestBody  Customer customer){
        customerService.addNewStudent(customer);

    }
    @DeleteMapping(path="{customerId}")
    public void  deleteCustomer(@PathVariable("customerId") Long id){
        customerService.deleteStudent(id);
    }

    @PutMapping(path="{customerId}")
    public void  updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required=false) String name,
            @RequestParam(required=false) String email)
    {
        customerService.updateStudent(customerId, name, email);
    }



}

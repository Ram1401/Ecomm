package com.example.ecomm.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomm.dao.CustomerRepository;
import com.example.ecomm.model.Customer;

@RequestMapping("api/v2/customer")
@RestController
public class CustomerNewController {

	@Autowired
	private CustomerRepository repository;
	
	@PostMapping
	public void addCustomer(@Valid @NonNull @RequestBody Customer customer) {
		
		repository.save(customer);
	}
	
	@GetMapping
	public List<Customer> getCustomers() {
		return repository.findAll();
	}
	
	@GetMapping(path = "{id}")
	public Customer getCustomerById(@PathVariable("id") Integer id) {
		return repository.findById(id).get();
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteCustomerById(@PathVariable("id") Integer id) {
		repository.deleteById(id);
	}
	
	@PutMapping(path = "{id}")
	public void updateCustomer(@PathVariable("id") Integer id, @Valid @NonNull @RequestBody Customer customer) {
		if(repository.existsById(id)) {
			Customer cust = repository.findById(id).get();
			cust.setName(customer.getName());
			repository.save(cust);
		}
	}
	
}

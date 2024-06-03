package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.dto.response.AnimalResponse;
import dev.nida.petclinic.entities.Animal;
import dev.nida.petclinic.service.abstracts.ICustomerService;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.CustomerRepo;
import dev.nida.petclinic.dto.request.CustomerRequest;
import dev.nida.petclinic.dto.response.CustomerResponse;
import dev.nida.petclinic.entities.Customer;
import dev.nida.petclinic.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;

    private final CustomerMapper customerMapper;

    @Override
    public ResponseEntity<List<CustomerResponse>> findAll() {

        List<Customer> customers = customerRepo.findAll();

        if (customers.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        List<CustomerResponse> customerResponses = customerMapper.asOutput(customers);

        return ResponseEntity.ok(customerResponses);

    }

    @Override
    public ResponseEntity<CustomerResponse> getById(long id) {

        Optional<Customer> customer = customerRepo.findById(id);

        if (customer.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        CustomerResponse customerResponse = customerMapper.asOutput(customer.get());

        return ResponseEntity.ok(customerResponse);

    }

    @Override
    public ResponseEntity<List<CustomerResponse>> getByName(String name) {

        List<Customer> customers = customerRepo.findByNameIgnoreCase(name);

        if (customers.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        List<CustomerResponse> customerResponses = customerMapper.asOutput(customers);

        return ResponseEntity.ok(customerResponses);
    }

    @Override
    public ResponseEntity<CustomerResponse> create(CustomerRequest request) {

        Optional<Customer> isCustomerExist = customerRepo.findByMail(request.getMail());

        if (isCustomerExist.isPresent()){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Customer customerSaved = customerRepo.save(customerMapper.asEntity(request));

        CustomerResponse customerResponse = customerMapper.asOutput(customerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);

    }

    @Override
    public ResponseEntity<CustomerResponse> update(long id, CustomerRequest request) {

        Optional<Customer> customerFromDb = customerRepo.findById(id);

        if (customerFromDb.isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        String newMail = request.getMail();

        Optional<Customer> newCustomer = customerRepo.findByMail(newMail);

        if (newCustomer.isPresent() && newCustomer.get().getId() != id){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Customer customer = customerFromDb.get();

        customerMapper.update(customer, request);

        Customer updatedCustomer = customerRepo.save(customer);

        CustomerResponse customerResponse = customerMapper.asOutput(updatedCustomer);

        return ResponseEntity.ok(customerResponse);

    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        Optional<Customer> customerFromDb = customerRepo.findById(id);

        if (customerFromDb.isPresent()){

            customerRepo.delete(customerFromDb.get());

            return ResponseEntity.ok().build();

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
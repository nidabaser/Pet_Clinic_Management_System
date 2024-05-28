package dev.nida.petclinic.service.concretes;

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
    public List<CustomerResponse> findAll() {

        return customerMapper.asOutput(customerRepo.findAll());

    }

    @Override
    public CustomerResponse getById(long id) {

        return customerMapper.asOutput(customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));

    }

    @Override
    public List<CustomerResponse> getByName(String name) {

        if (customerRepo.findByNameIgnoreCase(name).isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        return customerMapper.asOutput(customerRepo.findByNameIgnoreCase(name));

    }

    @Override
    public CustomerResponse create(CustomerRequest request) {

        Optional<Customer> isCustomerExist = customerRepo.findByMail(request.getMail());

        if (isCustomerExist.isEmpty()){

            Customer customerSaved = customerRepo.save(customerMapper.asEntity(request));

            return customerMapper.asOutput(customerSaved);

        }
        throw new DataExistsException(Msg.DATA_EXISTS);

    }

    @Override
    public CustomerResponse update(long id, CustomerRequest request) {

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

        return customerMapper.asOutput(customerRepo.save(customer));

    }

    @Override
    public void deleteById(long id) {

        Optional<Customer> customerFromDb = customerRepo.findById(id);

        if (customerFromDb.isPresent()){

            customerRepo.delete(customerFromDb.get());

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }
}
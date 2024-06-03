package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.CustomerRequest;
import dev.nida.petclinic.dto.response.CustomerResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface ICustomerService {

    ResponseEntity<List<CustomerResponse>> findAll();

    ResponseEntity<CustomerResponse> getById(long id);

    ResponseEntity<List<CustomerResponse>> getByName(String name);

    ResponseEntity<CustomerResponse> create(CustomerRequest request);

    ResponseEntity<CustomerResponse> update(long id, CustomerRequest request);

    ResponseEntity<Void> deleteById(long id);

}
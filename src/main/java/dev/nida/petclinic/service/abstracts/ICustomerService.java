package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.CustomerRequest;
import dev.nida.petclinic.dto.response.CustomerResponse;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface ICustomerService {

    public List<CustomerResponse> findAll();

    public CustomerResponse getById(long id);

    public List<CustomerResponse> getByName(String name);

    public CustomerResponse create(CustomerRequest request);

    public CustomerResponse update(long id, CustomerRequest request);

    public void deleteById(long id);

}
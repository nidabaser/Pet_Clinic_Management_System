package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.CustomerRequest;
import dev.nida.petclinic.dto.response.CustomerResponse;
import dev.nida.petclinic.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@Mapper
public interface CustomerMapper {

    Customer asEntity(CustomerRequest customerRequest);

    CustomerResponse asOutput(Customer customer);

    List<CustomerResponse> asOutput(List<Customer> customer);

    void update(@MappingTarget Customer entity, CustomerRequest request);

}

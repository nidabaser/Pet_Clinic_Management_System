package dev.nida.petclinic.dao;

import dev.nida.petclinic.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Optional<Customer> findByMail(String mail);

    List<Customer> findByNameIgnoreCase(String name);

}
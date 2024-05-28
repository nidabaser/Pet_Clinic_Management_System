package dev.nida.petclinic.dao;

import dev.nida.petclinic.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {

    Optional<Animal> findByCustomerIdAndName(long customerId, String name);

    List<Animal> findByCustomerName(String customerName);

    List<Animal> findByNameIgnoreCase(String name);

}

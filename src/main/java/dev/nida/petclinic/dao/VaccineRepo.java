package dev.nida.petclinic.dao;

import dev.nida.petclinic.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {

    Optional<Vaccine> findByNameAndCodeAndAnimalId(String name, String code, long animalId);

    List<Vaccine> findByAnimalId(long animalId);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

}

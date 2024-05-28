package dev.nida.petclinic.dao;

import dev.nida.petclinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByMail(String mail);

}

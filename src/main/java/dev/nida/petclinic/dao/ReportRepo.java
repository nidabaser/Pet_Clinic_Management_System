package dev.nida.petclinic.dao;

import dev.nida.petclinic.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nida Başer
 * June 2024
 */

@Repository
public interface ReportRepo extends JpaRepository<Report, Long> {

    Optional<Report> findByAppointmentId(Long appointmentId);

}

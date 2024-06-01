package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IAppointmentService {

    ResponseEntity<List<AppointmentResponse>> findAll();

    ResponseEntity<AppointmentResponse> getById(long id);

    ResponseEntity<AppointmentResponse> create(AppointmentRequest request);

    ResponseEntity<AppointmentResponse> update(long id, AppointmentRequest request);

    ResponseEntity<Void> deleteById(long id);

    ResponseEntity<Boolean> isDoctorAvailableAtTime(long doctorId, LocalDateTime appointmentDate);

    ResponseEntity<List<AppointmentResponse>> getAnimalAppointmentDateInRange(long animalId, LocalDate startDate, LocalDate endDate);

    ResponseEntity<List<AppointmentResponse>> getDoctorAppointmentDateInRange(long doctorId, LocalDate startDate, LocalDate endDate);

}
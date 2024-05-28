package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IAppointmentService {

    public List<AppointmentResponse> findAll();

    public AppointmentResponse getById(long id);

    public AppointmentResponse create(AppointmentRequest request);

    public AppointmentResponse update(long id, AppointmentRequest request);

    public void deleteById(long id);

    public boolean isDoctorAvailableAtTime(long doctorId, LocalDateTime appointmentDate);

    public List<AppointmentResponse> getAnimalAppointmentDateInRange(long animalId, LocalDate startDate, LocalDate endDate);

    public List<AppointmentResponse> getDoctorAppointmentDateInRange(long doctorId, LocalDate startDate, LocalDate endDate);

}
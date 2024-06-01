package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import dev.nida.petclinic.entities.Appointment;
import dev.nida.petclinic.dao.AppointmentRepo;
import dev.nida.petclinic.mapper.AppointmentMapper;
import dev.nida.petclinic.service.abstracts.IAppointmentService;
import dev.nida.petclinic.service.abstracts.IAvailableDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;

    private final AppointmentMapper appointmentMapper;

    private final IAvailableDateService availableDateManager;

    @Override
    public ResponseEntity<List<AppointmentResponse>> findAll() {

        List<Appointment> appointments = appointmentRepo.findAll();

        if (appointments.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<AppointmentResponse> appointmentResponses = appointmentMapper.asOutput(appointments);

        return ResponseEntity.ok(appointmentResponses);

    }

    @Override
    public ResponseEntity<AppointmentResponse> getById(long id) {

        Optional<Appointment> appointment = appointmentRepo.findById(id);

        if (appointment.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        AppointmentResponse appointmentResponse = appointmentMapper.asOutput(appointment.get());

        return ResponseEntity.ok(appointmentResponse);

    }

    @Override
    public ResponseEntity<AppointmentResponse> create(AppointmentRequest request) {

        if (!availableDateManager.existByDoctorIdAndAvailableDate(request.getDoctor().getId(), request.getAppointmentDate().toLocalDate())) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        if (!isDoctorAvailableAtTime(request.getDoctor().getId(), request.getAppointmentDate()).getBody()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        Appointment appointmentSaved = appointmentRepo.save(appointmentMapper.asEntity(request));

        AppointmentResponse appointmentResponse = appointmentMapper.asOutput(appointmentSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);

    }

    @Override
    public ResponseEntity<AppointmentResponse> update(long id, AppointmentRequest request) {

        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        Optional<Appointment> newAppointment = appointmentRepo.findByDoctorIdAndAppointmentDate(request.getDoctor().getId(), request.getAppointmentDate());

        if (newAppointment.isPresent() && newAppointment.get().getId() != id) {

            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }
        Appointment appointment = appointmentFromDb.get();

        appointmentMapper.update(appointment, request);

        AppointmentResponse updatedResponse = appointmentMapper.asOutput(appointmentRepo.save(appointment));

        return ResponseEntity.ok(updatedResponse);

    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isPresent()) {

            appointmentRepo.delete(appointmentFromDb.get());

            return ResponseEntity.noContent().build();

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Boolean> isDoctorAvailableAtTime(long doctorId, LocalDateTime appointmentDate) {

        boolean isAvailable = !appointmentRepo.existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);

        return ResponseEntity.ok(isAvailable);

    }

    @Override
    public ResponseEntity<List<AppointmentResponse>> getAnimalAppointmentDateInRange(long animalId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime);

        if (appointments.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<AppointmentResponse> appointmentResponses = appointmentMapper.asOutput(appointments);

        return ResponseEntity.ok(appointmentResponses);

    }

    @Override
    public ResponseEntity<List<AppointmentResponse>> getDoctorAppointmentDateInRange(long doctorId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);

        if (appointments.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<AppointmentResponse> appointmentResponses = appointmentMapper.asOutput(appointments);

        return ResponseEntity.ok(appointmentResponses);

    }
}
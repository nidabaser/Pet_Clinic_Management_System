package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.service.abstracts.IAppointmentService;
import dev.nida.petclinic.service.abstracts.IAvailableDateService;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.AppointmentRepo;
import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import dev.nida.petclinic.entities.Appointment;
import dev.nida.petclinic.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
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
    public List<AppointmentResponse> findAll() {

        return appointmentMapper.asOutput(appointmentRepo.findAll());
    }

    @Override
    public AppointmentResponse getById(long id) {

        return appointmentMapper.asOutput(appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {

        if (!availableDateManager.existByDoctorIdAndAvailableDate(request.getDoctor().getId(), request.getAppointmentDate().toLocalDate())){

            throw new NotFoundException(Msg.APPOINMENT_NOT_FOUND);

        }
        if (!isDoctorAvailableAtTime(request.getDoctor().getId(), request.getAppointmentDate())){

            throw new NotFoundException(Msg.APPOINMENT_NOT_FOUND);

        }
        Appointment appointmentSaved = appointmentRepo.save(appointmentMapper.asEntity(request));

        return appointmentMapper.asOutput(appointmentSaved);

    }

    @Override
    public AppointmentResponse update(long id, AppointmentRequest request) {

        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        Optional<Appointment> newAppointment = appointmentRepo.findByDoctorIdAndAppointmentDate(request.getDoctor().getId(), request.getAppointmentDate());

        if (newAppointment.isPresent() && newAppointment.get().getId() != id){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Appointment appointment = appointmentFromDb.get();

        appointmentMapper.update(appointment, request);

        return appointmentMapper.asOutput(appointmentRepo.save(appointment));

    }

    @Override
    public void deleteById(long id) {

        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);

        if (appointmentFromDb.isPresent()){

            appointmentRepo.delete(appointmentFromDb.get());

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }

    @Override
    public boolean isDoctorAvailableAtTime(long doctorId, LocalDateTime appointmentDate) {

        return !appointmentRepo.existsByDoctorIdAndAppointmentDate(doctorId, appointmentDate);

    }

    @Override
    public List<AppointmentResponse> getAnimalAppointmentDateInRange(long animalId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        return appointmentMapper.asOutput(appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime));

    }

    @Override
    public List<AppointmentResponse> getDoctorAppointmentDateInRange(long doctorId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);

        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);

        return appointmentMapper.asOutput(appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime));

    }

}

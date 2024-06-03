package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.service.abstracts.IDoctorService;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.DoctorRepo;
import dev.nida.petclinic.dto.request.DoctorRequest;
import dev.nida.petclinic.dto.response.DoctorResponse;
import dev.nida.petclinic.entities.Doctor;
import dev.nida.petclinic.mapper.DoctorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    private final DoctorMapper doctorMapper;

    @Override
    public ResponseEntity<List<DoctorResponse>> findAll() {

        List<Doctor> doctors = doctorRepo.findAll();

        if (doctors.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        List<DoctorResponse> doctorResponses = doctorMapper.asOutput(doctors);

        return ResponseEntity.ok(doctorResponses);

    }

    @Override
    public ResponseEntity<DoctorResponse> getById(long id) {

        Optional<Doctor> doctor = doctorRepo.findById(id);

        if (doctor.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        DoctorResponse doctorResponse = doctorMapper.asOutput(doctor.get());

        return ResponseEntity.ok(doctorResponse);
    }

    @Override
    public ResponseEntity<DoctorResponse> create(DoctorRequest request) {

        Optional<Doctor> isDoctorExist = doctorRepo.findByMail(request.getMail());

        if (isDoctorExist.isPresent()){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Doctor doctorSaved = doctorRepo.save(doctorMapper.asEntity(request));

        DoctorResponse doctorResponse = doctorMapper.asOutput(doctorSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponse);

    }

    @Override
    public ResponseEntity<DoctorResponse> update(long id, DoctorRequest request) {

        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);

        if (doctorFromDb.isEmpty()) {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        String newMail = request.getMail();

        Optional<Doctor> newDoctor = doctorRepo.findByMail(newMail);

        if (newDoctor.isPresent() && newDoctor.get().getId() != id) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Doctor doctor = doctorFromDb.get();

        doctorMapper.update(doctor, request);

        Doctor updatedDoctor = doctorRepo.save(doctor);

        DoctorResponse doctorResponse = doctorMapper.asOutput(updatedDoctor);

        return ResponseEntity.ok(doctorResponse);

    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);

        if (doctorFromDb.isPresent()){

            doctorRepo.delete(doctorFromDb.get());

            return ResponseEntity.ok().build();

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }
}
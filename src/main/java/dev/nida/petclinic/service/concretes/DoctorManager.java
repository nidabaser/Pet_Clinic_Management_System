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
    public List<DoctorResponse> findAll() {

        return doctorMapper.asOutput(doctorRepo.findAll());
    }

    @Override
    public DoctorResponse getById(long id) {

        return doctorMapper.asOutput(doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));
    }

    @Override
    public DoctorResponse create(DoctorRequest request) {

        Optional<Doctor> isDoctorExist = doctorRepo.findByMail(request.getMail());

        if (isDoctorExist.isEmpty()){

            Doctor doctorSaved = doctorRepo.save(doctorMapper.asEntity(request));

            return doctorMapper.asOutput(doctorSaved);

        }
        throw new DataExistsException(Msg.DATA_EXISTS);

    }

    @Override
    public DoctorResponse update(long id, DoctorRequest request) {

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

        return doctorMapper.asOutput(doctorRepo.save(doctor));

    }

    @Override
    public void deleteById(long id) {

        Optional<Doctor> doctorFromDb = doctorRepo.findById(id);

        if (doctorFromDb.isPresent()){

            doctorRepo.delete(doctorFromDb.get());

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }
}
package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.service.abstracts.IVaccineService;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.VaccineRepo;
import dev.nida.petclinic.dto.request.VaccineRequest;
import dev.nida.petclinic.dto.response.VaccineResponse;
import dev.nida.petclinic.entities.Vaccine;
import dev.nida.petclinic.mapper.VaccineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Nida Başer
 * May 2024
 */

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;

    private final VaccineMapper vaccineMapper;

    @Override
    public ResponseEntity<List<VaccineResponse>> findAll() {

        List<Vaccine> vaccines = vaccineRepo.findAll();

        if (vaccines.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<VaccineResponse> vaccineResponses = vaccineMapper.asOutput(vaccines);

        return ResponseEntity.ok(vaccineResponses);

    }

    @Override
    public ResponseEntity<VaccineResponse> getById(long id) {

        Optional<Vaccine> vaccine = vaccineRepo.findById(id);

        if (vaccine.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        VaccineResponse vaccineResponse = vaccineMapper.asOutput(vaccine.get());

        return ResponseEntity.ok(vaccineResponse);

    }

    @Override
    public ResponseEntity<List<VaccineResponse>> getByAnimal(long id) {

        List<Vaccine> vaccines = vaccineRepo.findByAnimalId(id);

        if (vaccines.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        List<VaccineResponse> vaccineResponses = vaccineMapper.asOutput(vaccines);

        return ResponseEntity.ok(vaccineResponses);

    }

    @Override
    public ResponseEntity<List<VaccineResponse>> getVaccinesInDateRange(LocalDate startDate, LocalDate endDate) {

        List<Vaccine> vaccines = vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate);

        if (vaccines.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<VaccineResponse> vaccineResponses = vaccineMapper.asOutput(vaccines);

        return ResponseEntity.ok(vaccineResponses);

    }

    @Override
    public ResponseEntity<VaccineResponse> create(VaccineRequest request) {

        Optional<Vaccine> isVaccineExist = vaccineRepo.findByNameAndCodeAndAnimalId(

                request.getName(), request.getCode(), request.getAnimal().getId()
        );

        if (isVaccineExist.isPresent()) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Vaccine vaccineSaved = vaccineRepo.save(vaccineMapper.asEntity(request));

        VaccineResponse vaccineResponse = vaccineMapper.asOutput(vaccineSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(vaccineResponse);

    }

    @Override
    public ResponseEntity<VaccineResponse> update(long id, VaccineRequest request) {

        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);

        if (vaccineFromDb.isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }

        Optional<Vaccine> newVaccine = vaccineRepo.findByNameAndCodeAndAnimalId(

                request.getName(), request.getCode(), request.getAnimal().getId()
        );

        if (newVaccine.isPresent() && newVaccine.get().getId() != id){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Vaccine vaccine = vaccineFromDb.get();

        vaccineMapper.update(vaccine, request);

        Vaccine updatedVaccine = vaccineRepo.save(vaccine);

        VaccineResponse vaccineResponse = vaccineMapper.asOutput(updatedVaccine);

        return ResponseEntity.ok(vaccineResponse);

    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);

        if (vaccineFromDb.isPresent()) {

            vaccineRepo.delete(vaccineFromDb.get());

            return ResponseEntity.ok().build();

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }
}
package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.service.abstracts.IAvailableDateService;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.AvailableDateRepo;
import dev.nida.petclinic.dto.request.AvailableDateRequest;
import dev.nida.petclinic.dto.response.AvailableDateResponse;
import dev.nida.petclinic.entities.AvailableDate;
import dev.nida.petclinic.mapper.AvailableDateMapper;
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
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;

    private final AvailableDateMapper availableDateMapper;

    @Override
    public ResponseEntity<List<AvailableDateResponse>> findAll() {

        List<AvailableDate> availableDates = availableDateRepo.findAll();

        if (availableDates.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<AvailableDateResponse> availableDateResponses = availableDateMapper.asOutput(availableDates);

        return ResponseEntity.ok(availableDateResponses);

    }

    @Override
    public ResponseEntity<AvailableDateResponse> getById(long id) {

        Optional<AvailableDate> availableDate = availableDateRepo.findById(id);

        if (availableDate.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        AvailableDateResponse availableDateResponse = availableDateMapper.asOutput(availableDate.get());

        return ResponseEntity.ok(availableDateResponse);
    }

    @Override
    public ResponseEntity<AvailableDateResponse> create(AvailableDateRequest request) {

        Optional<AvailableDate> isDateExist = availableDateRepo.findByDoctorIdAndAvailableDate(request.getDoctor().getId(), request.getAvailableDate());

        if (isDateExist.isPresent()) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        AvailableDate availableDateSaved = availableDateRepo.save(availableDateMapper.asEntity(request));

        AvailableDateResponse availableDateResponse = availableDateMapper.asOutput(availableDateSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(availableDateResponse);

    }

    @Override
    public ResponseEntity<AvailableDateResponse> update(long id, AvailableDateRequest request) {

        Optional<AvailableDate> dateFromDb = availableDateRepo.findById(id);

        if (dateFromDb.isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        long newDoctorId = request.getDoctor().getId();

        LocalDate newDate = request.getAvailableDate();

        Optional<AvailableDate> newAvailableDateObj = availableDateRepo.findByDoctorIdAndAvailableDate(newDoctorId, newDate);

        if (newAvailableDateObj.isPresent() && newAvailableDateObj.get().getId() != id){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        AvailableDate availableDate = dateFromDb.get();

        availableDateMapper.update(availableDate, request);

        AvailableDate updatedAvailableDate = availableDateRepo.save(availableDate);

        AvailableDateResponse availableDateResponse = availableDateMapper.asOutput(updatedAvailableDate);

        return ResponseEntity.ok(availableDateResponse);

    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {

        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);

        if (availableDateFromDb.isPresent()) {

            availableDateRepo.delete(availableDateFromDb.get());

            return ResponseEntity.ok().build();

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Boolean> existByDoctorIdAndAvailableDate(long doctorId, LocalDate availableDate) {

        boolean isAvailable = !availableDateRepo.existsByDoctorIdAndAvailableDate(doctorId, availableDate);

        return ResponseEntity.ok(isAvailable);
    }

    @Override
    public ResponseEntity<List<AvailableDateResponse>> getDoctorAvailableDateInRange(long doctorId, LocalDate startDate, LocalDate endDate) {

        List<AvailableDate> availableDates = availableDateRepo.findByDoctorIdAndAvailableDateBetween(doctorId, startDate, endDate);

        if (availableDates.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        List<AvailableDateResponse> availableDateResponses = availableDateMapper.asOutput(availableDates);

        return ResponseEntity.ok(availableDateResponses);
    }

}
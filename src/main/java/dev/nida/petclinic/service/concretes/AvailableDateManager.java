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
    public List<AvailableDateResponse> findAll() {

        return availableDateMapper.asOutput(availableDateRepo.findAll());

    }

    @Override
    public AvailableDateResponse getById(long id) {

        return availableDateMapper.asOutput(availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));

    }

    @Override
    public AvailableDateResponse create(AvailableDateRequest request) {

        Optional<AvailableDate> isDateExist = availableDateRepo.findByDoctorIdAndAvailableDate(request.getDoctor().getId(), request.getAvailableDate());

        if (isDateExist.isEmpty()){

            AvailableDate dateSaved = availableDateRepo.save(availableDateMapper.asEntity(request));

            return availableDateMapper.asOutput(dateSaved);

        }

        throw new DataExistsException(Msg.DATA_EXISTS);

    }

    @Override
    public AvailableDateResponse update(long id, AvailableDateRequest request) {

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

        return availableDateMapper.asOutput(availableDateRepo.save(availableDate));

    }

    @Override
    public void deleteById(long id) {

        Optional<AvailableDate> availableDateFromDb = availableDateRepo.findById(id);

        if (availableDateFromDb.isPresent()){

            availableDateRepo.delete(availableDateFromDb.get());

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }

    @Override
    public boolean existByDoctorIdAndAvailableDate(long doctorId, LocalDate availableDate) {

        return this.availableDateRepo.existsByDoctorIdAndAvailableDate(doctorId, availableDate);

    }

    @Override
    public List<AvailableDateResponse> getDoctorAvailableDateInRange(long doctorId, LocalDate startDate, LocalDate endDate) {

        return availableDateMapper.asOutput(availableDateRepo.findByDoctorIdAndAvailableDateBetween(doctorId, startDate, endDate));

    }

}
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
    public List<VaccineResponse> findAll() {

        return vaccineMapper.asOutput(vaccineRepo.findAll());
    }

    @Override
    public VaccineResponse getById(long id) {

        return vaccineMapper.asOutput(vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));
    }

    @Override
    public List<VaccineResponse> getByAnimal(long id) {

        if (vaccineRepo.findByAnimalId(id).isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        return vaccineMapper.asOutput(vaccineRepo.findByAnimalId(id));

    }

    @Override
    public List<VaccineResponse> getVaccinesInDateRange(LocalDate startDate, LocalDate endDate) {

        return vaccineMapper.asOutput(vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate));

    }

    @Override
    public VaccineResponse create(VaccineRequest request) {

        Optional<Vaccine> isVaccineExist = vaccineRepo.findByNameAndCodeAndAnimalId(

                request.getName(), request.getCode(), request.getAnimal().getId()
        );

        if (isVaccineExist.isEmpty()){

            Vaccine vaccineSaved = vaccineRepo.save(vaccineMapper.asEntity(request));

            return vaccineMapper.asOutput(vaccineSaved);

        } else {

            LocalDate today = LocalDate.now();

            LocalDate controlDate = isVaccineExist.get().getProtectionFinishDate();

            if (controlDate.isBefore(today) || controlDate.isEqual(today)){

                Vaccine vaccineSaved = vaccineRepo.save(vaccineMapper.asEntity(request));

                return vaccineMapper.asOutput(vaccineSaved);

            }
            throw new DataExistsException(Msg.PROTECTION_CONTINUES);

        }
    }

    @Override
    public VaccineResponse update(long id, VaccineRequest request) {

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
        vaccineMapper.update(vaccineFromDb.get(), request);

        return vaccineMapper.asOutput(vaccineRepo.save(vaccineFromDb.get()));

    }

    @Override
    public void deleteById(long id) {

        Optional<Vaccine> vaccineFromDb = vaccineRepo.findById(id);

        if (vaccineFromDb.isPresent()){

            vaccineRepo.delete(vaccineFromDb.get());

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }

}
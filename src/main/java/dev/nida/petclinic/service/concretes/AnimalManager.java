package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.service.abstracts.IAnimalService;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.AnimalRepo;
import dev.nida.petclinic.dto.request.AnimalRequest;
import dev.nida.petclinic.dto.response.AnimalResponse;
import dev.nida.petclinic.entities.Animal;
import dev.nida.petclinic.mapper.AnimalMapper;
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
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;

    private final AnimalMapper animalMapper;

    @Override
    public List<AnimalResponse> findAll() {

        return animalMapper.asOutput(animalRepo.findAll());

    }

    @Override
    public AnimalResponse getById(Long id) {

        return animalMapper.asOutput(animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));

    }

    @Override
    public List<AnimalResponse> getByCustomerName(String name) {

        if (animalRepo.findByCustomerName(name).isEmpty()) {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        return animalMapper.asOutput(animalRepo.findByCustomerName(name));

    }

    @Override
    public AnimalResponse create(AnimalRequest request) {

        Optional<Animal> isAnimalExist = animalRepo.findByCustomerIdAndName(request.getCustomer().getId(), request.getName());

        if (isAnimalExist.isEmpty()){

            Animal animalSaved = animalRepo.save(animalMapper.asEntity(request));

            return animalMapper.asOutput(animalSaved);

        }
        throw new DataExistsException(Msg.DATA_EXISTS);
    }

    @Override
    public AnimalResponse update(Long id, AnimalRequest request) {

        Optional<Animal> animalFromDb = animalRepo.findById(id);

        if (animalFromDb.isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        long newCustomerId = request.getCustomer().getId();

        String newName = request.getName();

        Optional<Animal> newAnimal = animalRepo.findByCustomerIdAndName(newCustomerId, newName);

        if (newAnimal.isPresent() && newAnimal.get().getId() != id){

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Animal animal = animalFromDb.get();

        animalMapper.update(animal, request);

        return animalMapper.asOutput(animalRepo.save(animal));

    }

    @Override
    public void deleteById(Long id) {

        Optional<Animal> animalFromDb = animalRepo.findById(id);

        if (animalFromDb.isPresent()){

            animalRepo.delete(animalFromDb.get());

        } else {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
    }

    @Override
    public List<AnimalResponse> getByName(String name) {

        if (animalRepo.findByNameIgnoreCase(name).isEmpty()){

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        return animalMapper.asOutput(animalRepo.findByNameIgnoreCase(name));

    }
}

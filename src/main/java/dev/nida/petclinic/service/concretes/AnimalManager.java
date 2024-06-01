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
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;

    private final AnimalMapper animalMapper;

    @Override
    public ResponseEntity<List<AnimalResponse>> findAll() {

        List<Animal> animals = animalRepo.findAll();

        if (animals.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        List<AnimalResponse> animalResponses = animalMapper.asOutput(animals);

        return ResponseEntity.ok(animalResponses);

    }

    @Override
    public ResponseEntity<AnimalResponse> getById(Long id) {

        Optional<Animal> animal = animalRepo.findById(id);

        if (animal.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        AnimalResponse animalResponse = animalMapper.asOutput(animal.get());

        return ResponseEntity.ok(animalResponse);

    }

    @Override
    public ResponseEntity<List<AnimalResponse>> getByCustomerName(String name) {

        List<Animal> animals = animalRepo.findByCustomerName(name);

        if (animals.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        List<AnimalResponse> animalResponses = animalMapper.asOutput(animals);

        return ResponseEntity.ok(animalResponses);

    }

    @Override
    public ResponseEntity<AnimalResponse> create(AnimalRequest request) {

        Optional<Animal> isAnimalExist = animalRepo.findByCustomerIdAndName(request.getCustomer().getId(), request.getName());

        if (isAnimalExist.isPresent()) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Animal animalSaved = animalRepo.save(animalMapper.asEntity(request));

        AnimalResponse animalResponse = animalMapper.asOutput(animalSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(animalResponse);

    }

    @Override
    public ResponseEntity<AnimalResponse> update(Long id, AnimalRequest request) {

        Optional<Animal> animalFromDb = animalRepo.findById(id);

        if (animalFromDb.isEmpty()) {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        long newCustomerId = request.getCustomer().getId();

        String newName = request.getName();

        Optional<Animal> newAnimal = animalRepo.findByCustomerIdAndName(newCustomerId, newName);

        if (newAnimal.isPresent() && newAnimal.get().getId() != id) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Animal animal = animalFromDb.get();

        animalMapper.update(animal, request);

        Animal updatedAnimal = animalRepo.save(animal);

        AnimalResponse animalResponse = animalMapper.asOutput(updatedAnimal);

        return ResponseEntity.ok(animalResponse);

    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {

        Optional<Animal> animalFromDb = animalRepo.findById(id);

        if (animalFromDb.isPresent()) {

            animalRepo.delete(animalFromDb.get());

            return ResponseEntity.ok().build();

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<List<AnimalResponse>> getByName(String name) {

        List<Animal> animals = animalRepo.findByNameIgnoreCase(name);

        if (animals.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        List<AnimalResponse> animalResponses = animalMapper.asOutput(animals);

        return ResponseEntity.ok(animalResponses);

    }

}
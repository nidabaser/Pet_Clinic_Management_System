package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.AnimalRequest;
import dev.nida.petclinic.dto.response.AnimalResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IAnimalService {

    ResponseEntity<List<AnimalResponse>> findAll();

    ResponseEntity<AnimalResponse> getById(Long id);

    ResponseEntity<List<AnimalResponse>> getByCustomerName(String name);

    ResponseEntity<AnimalResponse> create(AnimalRequest request);

    ResponseEntity<AnimalResponse> update(Long id, AnimalRequest request);

    ResponseEntity<Void> deleteById(Long id);

    ResponseEntity<List<AnimalResponse>> getByName(String name);

}
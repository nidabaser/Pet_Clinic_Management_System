package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.AnimalRequest;
import dev.nida.petclinic.dto.response.AnimalResponse;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IAnimalService {

    List<AnimalResponse> findAll();

    AnimalResponse getById(Long id);

    List<AnimalResponse> getByCustomerName(String name);

    AnimalResponse create(AnimalRequest request);

    AnimalResponse update(Long id, AnimalRequest request);

    void deleteById(Long id);

    List<AnimalResponse> getByName(String name);

}
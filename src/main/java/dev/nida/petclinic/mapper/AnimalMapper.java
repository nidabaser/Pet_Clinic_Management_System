package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.AnimalRequest;
import dev.nida.petclinic.dto.response.AnimalResponse;
import dev.nida.petclinic.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    Animal asEntity(AnimalRequest animalRequest);

    AnimalResponse asOutput(Animal animal);

    List<AnimalResponse> asOutput(List<Animal> animal);

    void update(@MappingTarget Animal entity, AnimalRequest request);

}

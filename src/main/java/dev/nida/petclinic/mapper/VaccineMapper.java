package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.VaccineRequest;
import dev.nida.petclinic.dto.response.VaccineResponse;
import dev.nida.petclinic.entities.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@Mapper
public interface VaccineMapper {

    Vaccine asEntity(VaccineRequest vaccineRequest);

    VaccineResponse asOutput(Vaccine vaccine);

    List<VaccineResponse> asOutput(List<Vaccine> vaccine);

    void update(@MappingTarget Vaccine entity, VaccineRequest request);

}
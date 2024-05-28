package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.AvailableDateRequest;
import dev.nida.petclinic.dto.response.AvailableDateResponse;
import dev.nida.petclinic.entities.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@Mapper
public interface AvailableDateMapper {

    AvailableDate asEntity(AvailableDateRequest availableDateRequest);

    AvailableDateResponse asOutput(AvailableDate availableDate);

    List<AvailableDateResponse> asOutput(List<AvailableDate> availableDate);

    void update(@MappingTarget AvailableDate entity, AvailableDateRequest request);

}

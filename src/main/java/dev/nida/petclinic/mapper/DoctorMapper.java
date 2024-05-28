package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.DoctorRequest;
import dev.nida.petclinic.dto.response.DoctorResponse;
import dev.nida.petclinic.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@Mapper
public interface DoctorMapper {

    Doctor asEntity(DoctorRequest doctorRequest);

    DoctorResponse asOutput(Doctor doctor);

    List<DoctorResponse> asOutput(List<Doctor> doctor);

    void update (@MappingTarget Doctor entity, DoctorRequest request);

}

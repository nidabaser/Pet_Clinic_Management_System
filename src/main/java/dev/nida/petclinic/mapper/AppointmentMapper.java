package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import dev.nida.petclinic.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@Mapper
public interface AppointmentMapper {

    Appointment asEntity (AppointmentRequest appointmentRequest);

    AppointmentResponse asOutput(Appointment appointment);

    List<AppointmentResponse> asOutput(List<Appointment> appointment);

    void update(@MappingTarget Appointment entity, AppointmentRequest request);

}

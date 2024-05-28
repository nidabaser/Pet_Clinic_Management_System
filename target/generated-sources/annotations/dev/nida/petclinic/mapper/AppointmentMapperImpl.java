package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import dev.nida.petclinic.entities.Appointment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment asEntity(AppointmentRequest appointmentRequest) {
        if ( appointmentRequest == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setAppointmentDate( appointmentRequest.getAppointmentDate() );
        appointment.setAnimal( appointmentRequest.getAnimal() );
        appointment.setDoctor( appointmentRequest.getDoctor() );

        return appointment;
    }

    @Override
    public AppointmentResponse asOutput(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentResponse appointmentResponse = new AppointmentResponse();

        if ( appointment.getId() != null ) {
            appointmentResponse.setId( appointment.getId() );
        }
        appointmentResponse.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentResponse.setAnimal( appointment.getAnimal() );
        appointmentResponse.setDoctor( appointment.getDoctor() );

        return appointmentResponse;
    }

    @Override
    public List<AppointmentResponse> asOutput(List<Appointment> appointment) {
        if ( appointment == null ) {
            return null;
        }

        List<AppointmentResponse> list = new ArrayList<AppointmentResponse>( appointment.size() );
        for ( Appointment appointment1 : appointment ) {
            list.add( asOutput( appointment1 ) );
        }

        return list;
    }

    @Override
    public void update(Appointment entity, AppointmentRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setAppointmentDate( request.getAppointmentDate() );
        entity.setAnimal( request.getAnimal() );
        entity.setDoctor( request.getDoctor() );
    }
}

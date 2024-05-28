package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.DoctorRequest;
import dev.nida.petclinic.dto.response.DoctorResponse;
import dev.nida.petclinic.entities.Doctor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public Doctor asEntity(DoctorRequest doctorRequest) {
        if ( doctorRequest == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setName( doctorRequest.getName() );
        doctor.setPhone( doctorRequest.getPhone() );
        doctor.setMail( doctorRequest.getMail() );
        doctor.setAddress( doctorRequest.getAddress() );
        doctor.setCity( doctorRequest.getCity() );

        return doctor;
    }

    @Override
    public DoctorResponse asOutput(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorResponse doctorResponse = new DoctorResponse();

        doctorResponse.setId( doctor.getId() );
        doctorResponse.setName( doctor.getName() );
        doctorResponse.setPhone( doctor.getPhone() );
        doctorResponse.setMail( doctor.getMail() );
        doctorResponse.setAddress( doctor.getAddress() );
        doctorResponse.setCity( doctor.getCity() );

        return doctorResponse;
    }

    @Override
    public List<DoctorResponse> asOutput(List<Doctor> doctor) {
        if ( doctor == null ) {
            return null;
        }

        List<DoctorResponse> list = new ArrayList<DoctorResponse>( doctor.size() );
        for ( Doctor doctor1 : doctor ) {
            list.add( asOutput( doctor1 ) );
        }

        return list;
    }

    @Override
    public void update(Doctor entity, DoctorRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setName( request.getName() );
        entity.setPhone( request.getPhone() );
        entity.setMail( request.getMail() );
        entity.setAddress( request.getAddress() );
        entity.setCity( request.getCity() );
    }
}

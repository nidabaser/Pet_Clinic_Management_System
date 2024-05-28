package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.VaccineRequest;
import dev.nida.petclinic.dto.response.VaccineResponse;
import dev.nida.petclinic.entities.Vaccine;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class VaccineMapperImpl implements VaccineMapper {

    @Override
    public Vaccine asEntity(VaccineRequest vaccineRequest) {
        if ( vaccineRequest == null ) {
            return null;
        }

        Vaccine vaccine = new Vaccine();

        vaccine.setName( vaccineRequest.getName() );
        vaccine.setCode( vaccineRequest.getCode() );
        vaccine.setProtectionStartDate( vaccineRequest.getProtectionStartDate() );
        vaccine.setProtectionFinishDate( vaccineRequest.getProtectionFinishDate() );
        vaccine.setAnimal( vaccineRequest.getAnimal() );

        return vaccine;
    }

    @Override
    public VaccineResponse asOutput(Vaccine vaccine) {
        if ( vaccine == null ) {
            return null;
        }

        VaccineResponse vaccineResponse = new VaccineResponse();

        vaccineResponse.setId( vaccine.getId() );
        vaccineResponse.setName( vaccine.getName() );
        vaccineResponse.setCode( vaccine.getCode() );
        vaccineResponse.setProtectionStartDate( vaccine.getProtectionStartDate() );
        vaccineResponse.setProtectionFinishDate( vaccine.getProtectionFinishDate() );
        vaccineResponse.setAnimal( vaccine.getAnimal() );

        return vaccineResponse;
    }

    @Override
    public List<VaccineResponse> asOutput(List<Vaccine> vaccine) {
        if ( vaccine == null ) {
            return null;
        }

        List<VaccineResponse> list = new ArrayList<VaccineResponse>( vaccine.size() );
        for ( Vaccine vaccine1 : vaccine ) {
            list.add( asOutput( vaccine1 ) );
        }

        return list;
    }

    @Override
    public void update(Vaccine entity, VaccineRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setName( request.getName() );
        entity.setCode( request.getCode() );
        entity.setProtectionStartDate( request.getProtectionStartDate() );
        entity.setProtectionFinishDate( request.getProtectionFinishDate() );
        entity.setAnimal( request.getAnimal() );
    }
}

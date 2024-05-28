package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.AvailableDateRequest;
import dev.nida.petclinic.dto.response.AvailableDateResponse;
import dev.nida.petclinic.entities.AvailableDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class AvailableDateMapperImpl implements AvailableDateMapper {

    @Override
    public AvailableDate asEntity(AvailableDateRequest availableDateRequest) {
        if ( availableDateRequest == null ) {
            return null;
        }

        AvailableDate availableDate = new AvailableDate();

        availableDate.setAvailableDate( availableDateRequest.getAvailableDate() );
        availableDate.setDoctor( availableDateRequest.getDoctor() );

        return availableDate;
    }

    @Override
    public AvailableDateResponse asOutput(AvailableDate availableDate) {
        if ( availableDate == null ) {
            return null;
        }

        AvailableDateResponse availableDateResponse = new AvailableDateResponse();

        availableDateResponse.setId( availableDate.getId() );
        availableDateResponse.setAvailableDate( availableDate.getAvailableDate() );
        availableDateResponse.setDoctor( availableDate.getDoctor() );

        return availableDateResponse;
    }

    @Override
    public List<AvailableDateResponse> asOutput(List<AvailableDate> availableDate) {
        if ( availableDate == null ) {
            return null;
        }

        List<AvailableDateResponse> list = new ArrayList<AvailableDateResponse>( availableDate.size() );
        for ( AvailableDate availableDate1 : availableDate ) {
            list.add( asOutput( availableDate1 ) );
        }

        return list;
    }

    @Override
    public void update(AvailableDate entity, AvailableDateRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setAvailableDate( request.getAvailableDate() );
        entity.setDoctor( request.getDoctor() );
    }
}

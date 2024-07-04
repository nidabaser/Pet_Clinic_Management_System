package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.ReportRequest;
import dev.nida.petclinic.dto.response.ReportResponse;
import dev.nida.petclinic.entities.Report;
import dev.nida.petclinic.entities.Vaccine;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class ReportMapperImpl implements ReportMapper {

    @Override
    public Report asEntity(ReportRequest request) {
        if ( request == null ) {
            return null;
        }

        Report report = new Report();

        report.setTitle( request.getTitle() );
        report.setDiagnosis( request.getDiagnosis() );
        report.setPrice( request.getPrice() );
        report.setAppointment( request.getAppointment() );

        return report;
    }

    @Override
    public ReportResponse asOutput(Report report) {
        if ( report == null ) {
            return null;
        }

        ReportResponse reportResponse = new ReportResponse();

        reportResponse.setId( report.getId() );
        reportResponse.setTitle( report.getTitle() );
        reportResponse.setDiagnosis( report.getDiagnosis() );
        reportResponse.setPrice( report.getPrice() );
        reportResponse.setAppointment( report.getAppointment() );
        List<Vaccine> list = report.getVaccine();
        if ( list != null ) {
            reportResponse.setVaccine( new ArrayList<Vaccine>( list ) );
        }

        return reportResponse;
    }

    @Override
    public List<ReportResponse> asOutput(List<Report> reports) {
        if ( reports == null ) {
            return null;
        }

        List<ReportResponse> list = new ArrayList<ReportResponse>( reports.size() );
        for ( Report report : reports ) {
            list.add( asOutput( report ) );
        }

        return list;
    }

    @Override
    public void update(Report entity, ReportRequest request) {
        if ( request == null ) {
            return;
        }

        entity.setTitle( request.getTitle() );
        entity.setDiagnosis( request.getDiagnosis() );
        entity.setPrice( request.getPrice() );
        entity.setAppointment( request.getAppointment() );
    }
}

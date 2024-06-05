package dev.nida.petclinic.mapper;

import dev.nida.petclinic.dto.request.ReportRequest;
import dev.nida.petclinic.dto.response.ReportResponse;
import dev.nida.petclinic.entities.Report;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author Nida Başer
 * June 2024
 */

@Mapper
public interface ReportMapper {

    Report asEntity(ReportRequest request);

    ReportResponse asOutput(Report report);

    List<ReportResponse> asOutput(List<Report> reports);

    void update(@MappingTarget Report entity, ReportRequest request);

}

package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.ReportRequest;
import dev.nida.petclinic.dto.response.ReportResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Nida Başer
 * June 2024
 */

public interface IReportService {

    ResponseEntity<List<ReportResponse>> findAll();

    ResponseEntity<ReportResponse> getById(Long id);

    ResponseEntity<ReportResponse> create(ReportRequest request);

    ResponseEntity<ReportResponse> update(Long id, ReportRequest request);

    ResponseEntity<Void> deleteById(Long id);

}

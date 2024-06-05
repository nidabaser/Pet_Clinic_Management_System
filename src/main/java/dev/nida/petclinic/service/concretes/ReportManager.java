package dev.nida.petclinic.service.concretes;

import dev.nida.petclinic.service.abstracts.IReportService;
import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.utilities.Msg;
import dev.nida.petclinic.dao.ReportRepo;
import dev.nida.petclinic.dto.request.ReportRequest;
import dev.nida.petclinic.dto.response.ReportResponse;
import dev.nida.petclinic.entities.Report;
import dev.nida.petclinic.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Nida Başer
 * June 2024
 */

@Service
@RequiredArgsConstructor
public class ReportManager implements IReportService {

    private final ReportRepo reportRepo;

    private final ReportMapper reportMapper;

    @Override
    public ResponseEntity<List<ReportResponse>> findAll(){

        List<Report> reports = reportRepo.findAll();

        if (reports.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        List<ReportResponse> reportResponses = reportMapper.asOutput(reports);

        return ResponseEntity.ok(reportResponses);

    };

    @Override
    public ResponseEntity<ReportResponse> getById(Long id){

        Optional<Report> report = reportRepo.findById(id);

        if (report.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        ReportResponse reportResponse = reportMapper.asOutput(report.get());

        return ResponseEntity.ok(reportResponse);

    }

    @Override
    public ResponseEntity<ReportResponse> create(ReportRequest request) {

        Optional<Report> isReportExist = reportRepo.findByAppointmentId(request.getAppointment().getId());

        if (isReportExist.isPresent()) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Report reportSaved = reportRepo.save(reportMapper.asEntity(request));

        ReportResponse reportResponse = reportMapper.asOutput(reportSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(reportResponse);

    }

    @Override
    public ResponseEntity<ReportResponse> update(Long id, ReportRequest request) {

        Optional<Report> reportFromDb = reportRepo.findById(id);

        if (reportFromDb.isEmpty()) {

            throw new NotFoundException(Msg.NOT_FOUND);

        }
        long newAppointmentId = request.getAppointment().getId();

        Optional<Report> newReport = reportRepo.findByAppointmentId(newAppointmentId);

        if (newReport.isPresent() && !Objects.equals(newReport.get().getId(), id)) {

            throw new DataExistsException(Msg.DATA_EXISTS);

        }
        Report report = reportFromDb.get();

        reportMapper.update(report, request);

        Report updatedReport = reportRepo.save(report);

        ReportResponse reportResponse = reportMapper.asOutput(updatedReport);

        return ResponseEntity.ok(reportResponse);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {

        Optional<Report> reportFromDb = reportRepo.findById(id);

        if (reportFromDb.isPresent()) {

            reportRepo.delete(reportFromDb.get());

            return ResponseEntity.ok().build();

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

}
package dev.nida.petclinic.controller;

import dev.nida.petclinic.service.abstracts.IReportService;
import dev.nida.petclinic.dto.request.ReportRequest;
import dev.nida.petclinic.dto.response.ReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nida Başer
 * June 2024
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final IReportService reportService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ReportResponse>> findAll(){
        return reportService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReportResponse> getById(@PathVariable Long id) {
        return reportService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ReportResponse> save(@RequestBody ReportRequest request) {
        return reportService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReportResponse> update(@PathVariable Long id, @RequestBody ReportRequest request) {
        return reportService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        reportService.deleteById(id);
    }

}

package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.VaccineRequest;
import dev.nida.petclinic.dto.response.VaccineResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IVaccineService {

    ResponseEntity<List<VaccineResponse>> findAll();

    ResponseEntity<VaccineResponse> getById(long id);

    ResponseEntity<List<VaccineResponse>> getByAnimal(long id);

    ResponseEntity<List<VaccineResponse>> getVaccinesInDateRange(LocalDate startDate, LocalDate endDate);

    ResponseEntity<VaccineResponse> create(VaccineRequest request);

    ResponseEntity<VaccineResponse> update(long id, VaccineRequest request);

    ResponseEntity<Void> deleteById(long id);

}
package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.AvailableDateRequest;
import dev.nida.petclinic.dto.response.AvailableDateResponse;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IAvailableDateService {

    ResponseEntity<List<AvailableDateResponse>> findAll();

    ResponseEntity<AvailableDateResponse> getById(long id);

    ResponseEntity<AvailableDateResponse> create(AvailableDateRequest request);

    ResponseEntity<AvailableDateResponse> update(long id, AvailableDateRequest request);

    ResponseEntity<Void> deleteById(long id);

    ResponseEntity<Boolean> existByDoctorIdAndAvailableDate(long doctorId, LocalDate availableDate);

    ResponseEntity<List<AvailableDateResponse>> getDoctorAvailableDateInRange(long doctorId, LocalDate startDate, LocalDate endDate);

}
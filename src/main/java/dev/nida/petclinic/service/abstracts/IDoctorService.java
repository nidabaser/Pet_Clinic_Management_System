package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.DoctorRequest;
import dev.nida.petclinic.dto.response.DoctorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IDoctorService {

    ResponseEntity<List<DoctorResponse>> findAll();

    ResponseEntity<DoctorResponse> getById(long id);

    ResponseEntity<DoctorResponse> create(DoctorRequest request);

    ResponseEntity<DoctorResponse> update(long id, DoctorRequest request);

    ResponseEntity<Void> deleteById(long id);

}

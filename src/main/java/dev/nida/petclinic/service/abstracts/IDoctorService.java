package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.DoctorRequest;
import dev.nida.petclinic.dto.response.DoctorResponse;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IDoctorService {

    public List<DoctorResponse> findAll();

    public DoctorResponse getById(long id);

    public DoctorResponse create(DoctorRequest request);

    public DoctorResponse update(long id, DoctorRequest request);

    public void deleteById(long id);

}

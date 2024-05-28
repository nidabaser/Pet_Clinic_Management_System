package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.VaccineRequest;
import dev.nida.petclinic.dto.response.VaccineResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IVaccineService {

    public List<VaccineResponse> findAll();

    public VaccineResponse getById(long id);

    public List<VaccineResponse> getByAnimal(long id);

    public List<VaccineResponse> getVaccinesInDateRange(LocalDate startDate, LocalDate endDate);

    public VaccineResponse create(VaccineRequest request);

    public VaccineResponse update(long id, VaccineRequest request);

    public void deleteById(long id);

}
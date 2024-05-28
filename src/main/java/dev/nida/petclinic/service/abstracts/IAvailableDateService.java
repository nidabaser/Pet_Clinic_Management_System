package dev.nida.petclinic.service.abstracts;

import dev.nida.petclinic.dto.request.AvailableDateRequest;
import dev.nida.petclinic.dto.response.AvailableDateResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

public interface IAvailableDateService {

    public List<AvailableDateResponse> findAll();

    public AvailableDateResponse getById(long id);

    public AvailableDateResponse create(AvailableDateRequest request);

    public AvailableDateResponse update(long id, AvailableDateRequest request);

    public void deleteById(long id);

    boolean existByDoctorIdAndAvailableDate(long doctorId, LocalDate availableDate);

    public List<AvailableDateResponse> getDoctorAvailableDateInRange(long doctorId, LocalDate startDate, LocalDate endDate);

}
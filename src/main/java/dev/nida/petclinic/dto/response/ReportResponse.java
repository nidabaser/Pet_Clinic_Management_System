package dev.nida.petclinic.dto.response;

import dev.nida.petclinic.entities.Appointment;
import dev.nida.petclinic.entities.Vaccine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Nida Başer
 * June 2024
 */

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ReportResponse {

    private Long id;

    private String title;

    private String diagnosis;

    private Double price;

    private Appointment appointment;

    private List<Vaccine> vaccine;

}

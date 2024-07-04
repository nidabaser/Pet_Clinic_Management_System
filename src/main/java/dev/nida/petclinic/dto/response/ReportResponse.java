package dev.nida.petclinic.dto.response;

import dev.nida.petclinic.entities.Appointment;
import dev.nida.petclinic.entities.Vaccine;
import lombok.*;

import java.util.List;

/**
 * @author Nida Başer
 * June 2024
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {

    private Long id;

    private String title;

    private String diagnosis;

    private Double price;

    private Appointment appointment;

    private List<Vaccine> vaccine;

}

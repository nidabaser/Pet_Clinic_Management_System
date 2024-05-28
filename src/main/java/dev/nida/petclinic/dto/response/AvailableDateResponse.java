package dev.nida.petclinic.dto.response;

import dev.nida.petclinic.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author Nida Başer
 * May 2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateResponse {

    private long id;

    private LocalDate availableDate;

    private Doctor doctor;

}

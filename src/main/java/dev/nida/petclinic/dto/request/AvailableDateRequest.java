package dev.nida.petclinic.dto.request;

import dev.nida.petclinic.entities.Doctor;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
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
public class AvailableDateRequest {

    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate availableDate;

    @NotNull
    private Doctor doctor;

}
package dev.nida.petclinic.dto.request;

import dev.nida.petclinic.entities.Animal;
import dev.nida.petclinic.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author Nida Başer
 * May 2024
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

    @NotNull
    private LocalDateTime appointmentDate;

    @NotNull
    private Animal animal;

    @NotNull
    private Doctor doctor;
}
package dev.nida.petclinic.dto.response;

import dev.nida.petclinic.entities.Animal;
import dev.nida.petclinic.entities.Doctor;
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
public class AppointmentResponse {

    private long id;

    private LocalDateTime appointmentDate;

    private Animal animal;

    private Doctor doctor;

}
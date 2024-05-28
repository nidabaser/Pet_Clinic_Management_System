package dev.nida.petclinic.dto.request;

import dev.nida.petclinic.entities.Animal;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class VaccineRequest {

    @NotBlank
    private String name;

    @NotNull
    private String code;

    @Temporal(TemporalType.DATE)
    @NotNull
    @PastOrPresent
    private LocalDate protectionStartDate;

    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate protectionFinishDate;

    @NotNull
    private Animal animal;

}

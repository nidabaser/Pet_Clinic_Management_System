package dev.nida.petclinic.dto.request;

import dev.nida.petclinic.entities.Appointment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

/**
 * @author Nida Başer
 * June 2024
 */

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ReportRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String diagnosis;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    private Appointment appointment;

}
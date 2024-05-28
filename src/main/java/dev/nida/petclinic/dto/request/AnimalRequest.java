package dev.nida.petclinic.dto.request;

import dev.nida.petclinic.entities.Animal;
import dev.nida.petclinic.entities.Customer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class AnimalRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    @NotBlank
    private String breed;

    @Enumerated(EnumType.STRING)
    private Animal.Gender gender;
    public enum Gender {
        MALE,
        FEMALE
    }

    @NotBlank
    private String colour;

    @Temporal(TemporalType.DATE)
    @Past(message = "The birth date must be in the past")
    private LocalDate dateOfBirth;

    @NotNull
    private Customer customer;

}
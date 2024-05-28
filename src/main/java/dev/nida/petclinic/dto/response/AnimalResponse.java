package dev.nida.petclinic.dto.response;

import dev.nida.petclinic.entities.Animal;
import dev.nida.petclinic.entities.Customer;
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
public class AnimalResponse {

    private long id;

    private String name;

    private String species;

    private String breed;

    private Animal.Gender gender;

    private String colour;

    private LocalDate dateOfBirth;

    private Customer customer;

}
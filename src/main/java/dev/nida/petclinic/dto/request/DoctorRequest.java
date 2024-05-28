package dev.nida.petclinic.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nida Başer
 * May 2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    @NotBlank
    private String name;

    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Invalid phone number format. Please use XXX-XXX-XXXX format.")
    private String phone;

    @Email
    private String mail;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

}
package dev.nida.petclinic.dto.response;

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
public class DoctorResponse {

    private long id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

}
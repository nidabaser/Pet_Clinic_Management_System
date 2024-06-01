package dev.nida.petclinic.controller;

import dev.nida.petclinic.service.abstracts.IAppointmentService;
import dev.nida.petclinic.dto.request.AppointmentRequest;
import dev.nida.petclinic.dto.response.AppointmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Nida Başer
 * May 2024
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AppointmentResponse>> findAll(){
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppointmentResponse> getById(@PathVariable("id") long id){
        return appointmentService.getById(id);
    }

    @GetMapping("/animal/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AppointmentResponse>> getAnimalAppointmentDateInRange(
            @RequestParam long animalId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        return appointmentService.getAnimalAppointmentDateInRange(animalId, startDate, endDate);
    }

    @GetMapping("/doctor/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AppointmentResponse>> getDoctorAppointmentDateInRange(
            @RequestParam long doctorId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        return appointmentService.getDoctorAppointmentDateInRange(doctorId, startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AppointmentResponse> save(@RequestBody AppointmentRequest request){
        return appointmentService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AppointmentResponse> update(@PathVariable("id") long id, @RequestBody AppointmentRequest request){
        return appointmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id){
        appointmentService.deleteById(id);
    }
}
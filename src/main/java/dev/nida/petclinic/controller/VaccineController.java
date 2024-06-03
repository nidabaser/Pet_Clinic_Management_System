package dev.nida.petclinic.controller;

import dev.nida.petclinic.service.abstracts.IVaccineService;
import dev.nida.petclinic.dto.request.VaccineRequest;
import dev.nida.petclinic.dto.response.VaccineResponse;
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
@RequestMapping("/api/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VaccineResponse>> findAll(){
        return vaccineService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VaccineResponse> getById(@PathVariable("id") long id){
        return vaccineService.getById(id);
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VaccineResponse>> getByAnimal(@PathVariable("id") long id){
        return vaccineService.getByAnimal(id);
    }

    @GetMapping("/date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<VaccineResponse>> getVaccineInDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ){
        return vaccineService.getVaccinesInDateRange(startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VaccineResponse> save(@RequestBody VaccineRequest request){
        return vaccineService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<VaccineResponse> update(@PathVariable("id") long id, @RequestBody VaccineRequest request){
        return vaccineService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id){
        vaccineService.deleteById(id);
    }

}
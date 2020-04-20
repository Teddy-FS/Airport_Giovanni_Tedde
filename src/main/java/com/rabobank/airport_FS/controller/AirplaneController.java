package com.rabobank.airport_FS.controller;

import com.rabobank.airport_FS.model.Airplane;
import com.rabobank.airport_FS.repositories.AirplaneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirplaneController {

    private AirplaneRepository airplaneRepository;

    public AirplaneController(AirplaneRepository repo){
        this.airplaneRepository = repo;
        addMockPlanes();
    }

    private void addMockPlanes() {
        this.airplaneRepository.save(new Airplane("1231ASAP","Rome",5));
        this.airplaneRepository.save(new Airplane("98789asdsadasda","Berlin",5));
        this.airplaneRepository.save(new Airplane("AAA123","Stockholm",5));
    }

    @GetMapping("/airplanes")
    public List<Airplane> getPlanes(){
        return this.airplaneRepository.findAll();
    }

    @PostMapping
    public Airplane addPlane(@RequestBody Airplane airplane){
        //The passed object is not used directly to make sure the current fuel level is <= the tank capacity
        return this.airplaneRepository.save(new Airplane(airplane.getId(),airplane.getCurrentAirport(),airplane.getFuelTank()));
    }

    @PostMapping("/airplane/")
    public void refillPlane(@RequestBody Airplane airplane){
        airplane.refuel();
        this.airplaneRepository.save(airplane);
    }

    @PostMapping("/airplane/fly")
    public boolean flyPlane(String id,String destination){
        if(this.airplaneRepository.existsById(id)) {
            Airplane airplane = this.airplaneRepository.findById(id).get();
            if(airplane.fly(destination, 2)){
                this.airplaneRepository.save(airplane);
                return true;
            }
        }
            return false;
    }

    @GetMapping("/airplane/{id}")
    public Airplane getAirplane(@PathVariable String id){
        return this.airplaneRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/airplane/{id}")
    public String deleteAirplane(@PathVariable String id){
        if(this.airplaneRepository.existsById(id)) {
            this.airplaneRepository.deleteById(id);
            return "Plane deleted";
        }else
            return "Plane not found";
    }

}

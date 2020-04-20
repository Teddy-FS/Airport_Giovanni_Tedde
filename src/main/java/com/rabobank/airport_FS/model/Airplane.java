package com.rabobank.airport_FS.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Airplane {

    @Id
    private String id;
    private int fuelTank = 5;
    private int fuel;
    @Setter
    private String currentAirport;

    public Airplane(){
    }

    public Airplane(String id, String currentAirport, int fuelTank){ //A fixed size for the fuel tank does not sound very future proof
        this.id = id;
        this.fuelTank = fuelTank;
        this.currentAirport = currentAirport;
        this.refuel();
    }

    public boolean fly(String destination, int fuel){//Same for the required fuel. What if XYZ gets bigger?
        if(this.fuel < fuel)
            return false;
        else {
            this.fuel -= fuel;
            this.currentAirport = destination;
            return true;
        }
    }

    public boolean refuel(){
        if(this.fuel<this.fuelTank) {
            this.fuel = fuelTank;
            return true;
        }else
            return false;
    }
}

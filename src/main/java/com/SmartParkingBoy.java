package com;

import com.parkingLot.Car;
import com.parkingLot.ParkingLot;
import com.parkingLot.ParkingTicket;

import java.util.Map;

public class SmartParkingBoy {
    private Map<String, ParkingLot> parkingLots;

    public SmartParkingBoy(Map<String, ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return new ParkingTicket(car.getNumber(), "A");
    }
}

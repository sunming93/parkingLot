package com.parkingLot;

public class ParkingLot {
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        return new ParkingTicket(car.getNumber());
    }
}

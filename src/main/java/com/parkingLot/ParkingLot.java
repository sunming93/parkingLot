package com.parkingLot;

import com.exceptions.NoNumberException;

public class ParkingLot {
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) throws NoNumberException {
        if (car.getNumber() == null) {
            throw new NoNumberException();
        }
        return new ParkingTicket(car.getNumber());
    }
}

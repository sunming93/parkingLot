package com.parkingLot;

import com.exceptions.NoAvailableException;
import com.exceptions.NoNumberException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<String, Car> cars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashMap<>();
    }

    public ParkingTicket park(Car car) throws Exception {
        if (cars.size() >= capacity) {
            throw new NoAvailableException();
        }
        if (car.getNumber() == null) {
            throw new NoNumberException();
        }
        cars.put(car.getNumber(), car);
        return new ParkingTicket(car.getNumber());
    }
}

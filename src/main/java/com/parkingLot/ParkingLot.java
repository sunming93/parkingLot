package com.parkingLot;

import com.exceptions.DuplicatedCarException;
import com.exceptions.NoAvailablePositionException;
import com.exceptions.NoCarException;
import com.exceptions.NoNumberException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    private int capacity;
    private Map<String, Car> cars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashMap<>();
    }

    public ParkingTicket park(Car car) throws Exception {
        if (cars.size() >= capacity) {
            throw new NoAvailablePositionException();
        }
        if (car.getNumber() == null) {
            throw new NoNumberException();
        }
        if (Objects.nonNull(cars.get(car.getNumber()))) {
            throw new DuplicatedCarException();
        }
        cars.put(car.getNumber(), car);
        return new ParkingTicket(car.getNumber());
    }

    public Car pick(ParkingTicket ticket) throws Exception {
        if (!cars.containsKey(ticket.getId())) {
            throw new NoCarException();
        }
        return cars.get(ticket.getId());
    }
}

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
    private String name;
    private Map<String, Car> cars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashMap<>();
    }

    public ParkingLot(int capacity, String name) {
        this.capacity = capacity;
        this.name = name;
        cars = new HashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableLotNumber() {
        return capacity - cars.size();
    }

    public String getName() {
        return name;
    }

    public Map<String, Car> getCars() {
        return cars;
    }

    public ParkingTicket park(Car car) throws Exception {
        validateAvailablePosition();
        validateNoNumber(car);
        validateDuplicatedCar(car);
        cars.put(car.getNumber(), car);
        return new ParkingTicket(car.getNumber());
    }

    public Car pick(ParkingTicket ticket) throws Exception {
        if (!cars.containsKey(ticket.getId())) {
            throw new NoCarException();
        }
        return cars.get(ticket.getId());
    }

    private void validateAvailablePosition() throws NoAvailablePositionException {
        if (cars.size() >= capacity) {
            throw new NoAvailablePositionException();
        }
    }

    private void validateNoNumber(Car car) throws NoNumberException {
        if (car.getNumber() == null) {
            throw new NoNumberException();
        }
    }

    private void validateDuplicatedCar(Car car) throws DuplicatedCarException {
        if (Objects.nonNull(cars.get(car.getNumber()))) {
            throw new DuplicatedCarException();
        }
    }
}

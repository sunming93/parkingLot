package com;

import com.exceptions.DuplicatedCarException;
import com.exceptions.NoAvailablePositionException;
import com.parkingLot.Car;
import com.parkingLot.ParkingLot;
import com.parkingLot.ParkingTicket;

import java.util.Map;

public class GraduateParkingBoy {
    private Map<String, ParkingLot> parkingLots;

    public GraduateParkingBoy(Map<String, ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) throws Exception {
        ParkingLot firstAvailableParkingLot = parkingLots.values().stream()
                .filter(parkingLot -> parkingLot.getCars().size() < parkingLot.getCapacity())
                .findFirst()
                .orElseThrow(() -> new NoAvailablePositionException());

        if (parkingLots.values().stream().anyMatch(parkingLot -> parkingLot.getCars().containsKey(car.getNumber()))) {
            throw new DuplicatedCarException();
        }

        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }
}

package com;

import com.exceptions.NoAvailablePositionException;
import com.parkingLot.Car;
import com.parkingLot.ParkingLot;
import com.parkingLot.ParkingTicket;

import java.util.Comparator;
import java.util.Map;

public class SmartParkingBoy {
    private Map<String, ParkingLot> parkingLots;

    public SmartParkingBoy(Map<String, ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) throws Exception {
        ParkingLot firstAvailableParkingLot = parkingLots.values().stream()
                .filter(parkingLot -> parkingLot.getCars().size() < parkingLot.getCapacity())
                .sorted(Comparator.comparingInt(ParkingLot::getAvailableLotNumber).reversed())
                .findFirst()
                .orElseThrow(() -> new NoAvailablePositionException());

        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }
}

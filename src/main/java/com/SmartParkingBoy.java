package com;

import com.exceptions.DuplicatedCarException;
import com.exceptions.NoAvailablePositionException;
import com.exceptions.NoCarException;
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

        if (parkingLots.values().stream().anyMatch(parkingLot -> parkingLot.getCars().containsKey(car.getNumber()))) {
            throw new DuplicatedCarException();
        }


        ParkingTicket ticket = firstAvailableParkingLot.park(car);
        ticket.setParkingLotName(firstAvailableParkingLot.getName());
        return ticket;
    }

    public Car pick(ParkingTicket ticket) throws Exception {
        ParkingLot parkingLot = parkingLots.get(ticket.getParkingLotName());
        if (parkingLot == null) {
            throw new NoCarException();
        }
        return parkingLot.pick(ticket);
    }
}

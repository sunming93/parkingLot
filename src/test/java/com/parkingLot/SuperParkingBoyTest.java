package com.parkingLot;

import com.SmartParkingBoy;
import com.SuperParkingBoy;
import com.exceptions.DuplicatedCarException;
import com.exceptions.NoNumberException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest {
    @Test
    void should_return_ticket_of_a_when_park_given_100percent_available_position_in_park_lot_a_and_50percent_available_position_in_park_lot_b_and_unique_number() throws Exception {
        ParkingLot parkingLotB = new ParkingLot(2, "B");
        parkingLotB.park(new Car("A1234"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", parkingLotB);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        ParkingTicket ticket = superParkingBoy.park(new Car("A12345"));

        assertEquals("A12345", ticket.getId());
        assertEquals("A",ticket.getParkingLotName());
    }

    @Test
    void should_return_ticket_of_b_when_park_given_50percent_available_position_in_park_lot_a_and_100percent_available_position_in_park_lot_b_and_unique_number() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(2, "A");
        parkingLotA.park(new Car("A1234"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", parkingLotA);
        parkingLots.put("B", new ParkingLot(1, "B"));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        ParkingTicket ticket = superParkingBoy.park(new Car("A12345"));

        assertEquals("A12345", ticket.getId());
        assertEquals("B",ticket.getParkingLotName());
    }

    @Test
    void should_throw_no_number_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_no_number() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", new ParkingLot(1, "B"));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        assertThrows(NoNumberException.class,() -> superParkingBoy.park(new Car()));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_a() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(2, "A");
        parkingLotA.park(new Car("A12345"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", parkingLotA);
        parkingLots.put("B", new ParkingLot(1, "B"));
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        assertThrows(DuplicatedCarException.class,() -> superParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_b() throws Exception {
        ParkingLot parkingLotB = new ParkingLot(2, "B");
        parkingLotB.park(new Car("A12345"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", parkingLotB);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        assertThrows(DuplicatedCarException.class,() -> superParkingBoy.park(new Car("A12345")));
    }
}

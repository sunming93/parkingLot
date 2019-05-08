package com.parkingLot;

import com.SmartParkingBoy;
import com.exceptions.DuplicatedCarException;
import com.exceptions.NoNumberException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    void should_return_ticket_of_a_when_park_given_2_available_position_in_park_lot_a_and_1_available_position_in_park_lot_b_and_unique_number() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(2, "A"));
        parkingLots.put("B", new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket ticket = smartParkingBoy.park(new Car("A12345"));

        assertEquals("A12345", ticket.getId());
        assertEquals("A",ticket.getParkingLotName());
    }

    @Test
    void should_return_ticket_of_b_when_park_given_1_available_position_in_park_lot_a_and_2_available_position_in_park_lot_b_and_unique_number() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", new ParkingLot(2, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        ParkingTicket ticket = smartParkingBoy.park(new Car("A12345"));

        assertEquals("A12345", ticket.getId());
        assertEquals("B",ticket.getParkingLotName());
    }

    @Test
    void should_throw_no_number_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_no_number() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertThrows(NoNumberException.class,() -> smartParkingBoy.park(new Car()));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_a() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(2, "A");
        parkingLotA.park(new Car("A12345"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", parkingLotA);
        parkingLots.put("B", new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertThrows(DuplicatedCarException.class,() -> smartParkingBoy.park(new Car("A12345")));
    }
}

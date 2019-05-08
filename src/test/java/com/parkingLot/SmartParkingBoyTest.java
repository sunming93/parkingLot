package com.parkingLot;

import com.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

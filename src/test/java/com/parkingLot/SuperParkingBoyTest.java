package com.parkingLot;

import com.SuperParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}

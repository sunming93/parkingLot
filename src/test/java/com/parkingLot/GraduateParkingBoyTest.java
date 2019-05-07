package com.parkingLot;

import com.GraduateParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraduateParkingBoyTest {
    @Test
    void should_return_ticket_of_a_when_park_given_available_position_in_park_lot_a_and_unique_number() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", new ParkingLot(1, "B"));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLots);
        ParkingTicket ticket = graduateParkingBoy.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
        assertEquals("A",ticket.getParkingLotName());
    }
}

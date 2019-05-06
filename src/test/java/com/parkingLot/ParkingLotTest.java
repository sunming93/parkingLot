package com.parkingLot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_available_position_and_unique_number() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingTicket ticket = parkingLot.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
    }
}

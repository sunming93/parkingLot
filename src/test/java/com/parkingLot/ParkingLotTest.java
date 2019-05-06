package com.parkingLot;

import com.exceptions.NoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_available_position_and_unique_number() throws NoNumberException {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingTicket ticket = parkingLot.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
    }

    @Test
    void should_throw_no_number_exception_when_park_given_available_position_and_no_number() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertThrows(NoNumberException.class,() -> parkingLot.park(new Car()));
    }
}

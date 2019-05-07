package com.parkingLot;

import com.exceptions.NoAvailableException;
import com.exceptions.NoNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_available_position_and_unique_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingTicket ticket = parkingLot.park(new Car("A12345"));
        assertEquals("A12345", ticket.getId());
    }

    @Test
    void should_throw_no_number_exception_when_park_given_available_position_and_no_number() {
        ParkingLot parkingLot = new ParkingLot(1);
        assertThrows(NoNumberException.class,() -> parkingLot.park(new Car()));
    }

    @Test
    void should_throw_no_available_exception_when_park_given_not_available_position_and_unique_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("B12345"));
        assertThrows(NoAvailableException.class,() -> parkingLot.park(new Car("A1123")));
    }
}

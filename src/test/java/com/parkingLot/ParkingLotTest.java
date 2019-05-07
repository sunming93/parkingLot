package com.parkingLot;

import com.exceptions.DuplicatedCarException;
import com.exceptions.NoAvailablePositionException;
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
    void should_throw_no_available_position_exception_when_park_given_not_available_position_and_unique_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car("B12345"));
        assertThrows(NoAvailablePositionException.class,() -> parkingLot.park(new Car("A1123")));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_and_duplicated_number() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car("A1123"));
        assertThrows(DuplicatedCarException.class,() -> parkingLot.park(new Car("A1123")));
    }

    @Test
    void should_return_the_car_when_pick_car_given_valid_parking_ticket() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("A1123");
        ParkingTicket ticket = parkingLot.park(car);
        Car actualCar = parkingLot.pick(ticket);
        assertEquals(actualCar, car);
    }
}

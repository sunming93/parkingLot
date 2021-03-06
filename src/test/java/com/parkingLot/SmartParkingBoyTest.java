package com.parkingLot;

import com.SmartParkingBoy;
import com.exceptions.DuplicatedCarException;
import com.exceptions.NoAvailablePositionException;
import com.exceptions.NoCarException;
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

    @Test
    void should_throw_duplicated_car_exception_when_park_given_available_position_in_park_lot_b_and_available_position_in_park_lot_a_and_duplicated_number_in_parking_lot_b() throws Exception {
        ParkingLot parkingLotB = new ParkingLot(2, "B");
        parkingLotB.park(new Car("A12345"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertThrows(DuplicatedCarException.class,() -> smartParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_throw_no_available_exception_when_park_given_not_available_position_in_park_lot_b_and_not_available_position_in_park_lot_a_and_unique_number() throws Exception {
        ParkingLot parkingLotA = new ParkingLot(1, "A");
        parkingLotA.park(new Car("A123"));
        ParkingLot parkingLotB = new ParkingLot(1, "B");
        parkingLotB.park(new Car("A1234"));
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", parkingLotA);
        parkingLots.put("B", parkingLotB);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        assertThrows(NoAvailablePositionException.class,() -> smartParkingBoy.park(new Car("A12345")));
    }

    @Test
    void should_return_the_right_car_when_pick_car_given_valid_parking_ticket() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car("A1123");
        ParkingTicket ticket = smartParkingBoy.park(car);

        Car actualCar = smartParkingBoy.pick(ticket);

        assertEquals(actualCar, car);
    }

    @Test
    void should_throw_no_car_exception_when_pick_car_given_invalid_parking_ticket() throws Exception {
        Map<String, ParkingLot> parkingLots = new HashMap<>();
        parkingLots.put("A", new ParkingLot(1, "A"));
        parkingLots.put("B", new ParkingLot(1, "B"));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        ParkingTicket invalidTicket = new ParkingTicket("hack", "hack");

        assertThrows(NoCarException.class, () -> smartParkingBoy.pick(invalidTicket));
    }
}

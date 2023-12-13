package parking.lot;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    void shouldBeAbleToParkCar() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1000);

        parkingLot.park(new Car());
    }

    @Test
    void shouldThrowParkingNotAvailableExceptionForNoCapacity() throws ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(3);

        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Car()));
    }

}
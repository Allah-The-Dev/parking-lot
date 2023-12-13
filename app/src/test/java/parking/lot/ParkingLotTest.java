package parking.lot;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    void shouldBeAbleToParkCar() throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1000);

        parkingLot.park(new Car());
    }

    @Test
    void shouldThrowParkingNotAvailableExceptionForNoCapacity()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(3);

        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Car()));
    }

    @Test
    void shouldThrowCarAlreadyParkedExceptionForParkingSameCarAgain()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(4);

        parkingLot.park(new Car());
        parkingLot.park(new Car());

        Car car3 = new Car();
        parkingLot.park(car3);

        assertThrows(CarAlreadyParkedException.class, () -> parkingLot.park(car3));

    }

}
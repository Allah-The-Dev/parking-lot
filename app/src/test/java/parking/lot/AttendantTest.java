package parking.lot;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AttendantTest {
    @Test
    void attendantShouldBeAbleToParkCarInFirstAvailableParkingLot()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLotMock);

        Car carMock = Mockito.mock(Car.class);

        attendant.park(carMock);

    }

    @Test
    void attendantShouldNotBeAbleToParkCarInFirstParkingLotWithNoCapacity()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLotMock);

        Car carMock = Mockito.mock(Car.class);
        Mockito.doThrow(ParkingLotFullException.class).when(parkingLotMock).park(carMock);

        assertThrows(ParkingLotFullException.class, () -> attendant.park(carMock));

    }

}

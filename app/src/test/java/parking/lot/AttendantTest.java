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

    @Test
    void attendantShouldBeAbleToParkCarInFirstAvailableParkingLotInMultipleParkingLots()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock2 = Mockito.mock(ParkingLot.class);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLotMock);
        attendant.addParkingLot(parkingLotMock2);

        Car carMock = Mockito.mock(Car.class);

        Mockito.when(parkingLotMock.isFull()).thenReturn(true);
        Mockito.when(parkingLotMock2.isFull()).thenReturn(false);

        attendant.park(carMock);
        Mockito.verify(parkingLotMock2, Mockito.times(1)).park(carMock);

    }

    @Test
    void attendantShouldBeAbleToUnparkForParkedCar()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);

        Car carMock = Mockito.mock(Car.class);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLotMock);

        Mockito.when(parkingLotMock.isFull()).thenReturn(false);
        Mockito.when(parkingLotMock.isParked(carMock)).thenReturn(true);

        attendant.park(carMock);
        attendant.unpark(carMock);
        Mockito.verify(parkingLotMock, Mockito.times(1)).unpark(carMock);
    }

}

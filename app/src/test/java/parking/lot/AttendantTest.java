package parking.lot;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AttendantTest {
    @Test
    void attendantShouldBeAbleToParkCarInFirstAvailableParkingLot()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        ParkingLotSelector parkingLotSelectorMock = Mockito.mock(FirstFreeParkingLotSelctor.class);

        Mockito.when(parkingLotSelectorMock.select(anyList())).thenReturn(parkingLotMock);

        Attendant attendant = new Attendant(parkingLotSelectorMock);
        attendant.addParkingLot(parkingLotMock);

        Car carMock = Mockito.mock(Car.class);

        attendant.park(carMock);

    }

    @Test
    void attendantShouldNotBeAbleToParkCarInFirstParkingLotWithNoCapacity()
            throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        ParkingLotSelector parkingLotSelectorMock = Mockito.mock(FirstFreeParkingLotSelctor.class);

        Mockito.when(parkingLotSelectorMock.select(anyList())).thenReturn(parkingLotMock);

        Attendant attendant = new Attendant(parkingLotSelectorMock);
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

        ParkingLotSelector parkingLotSelectorMock = Mockito.mock(FirstFreeParkingLotSelctor.class);

        Mockito.when(parkingLotSelectorMock.select(anyList())).thenReturn(parkingLotMock2);

        Attendant attendant = new Attendant(parkingLotSelectorMock);
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

        Mockito.when(parkingLotMock.isParked(carMock)).thenReturn(true);

        attendant.unpark(carMock);
        Mockito.verify(parkingLotMock, Mockito.times(1)).unpark(carMock);
    }

    @Test
    void attendantShouldBeAbleToUnparkForParkedCarFromMultipleParkingLots()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock2 = Mockito.mock(ParkingLot.class);

        Car carMock = Mockito.mock(Car.class);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLotMock);
        attendant.addParkingLot(parkingLotMock2);

        Mockito.when(parkingLotMock.isParked(carMock)).thenReturn(false);
        Mockito.when(parkingLotMock2.isParked(carMock)).thenReturn(true);

        attendant.unpark(carMock);
        Mockito.verify(parkingLotMock2, Mockito.times(1)).unpark(carMock);
    }

    @Test
    void attendantShouldBeAbleToParkCarUsingLeastCarsParkedSelector()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock2 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock3 = Mockito.mock(ParkingLot.class);

        ParkingLotSelector parkingLotSelector = new LeastCarsParkedSelector();

        Mockito.when(parkingLotMock.getParkedCarsCount()).thenReturn(2);
        Mockito.when(parkingLotMock2.getParkedCarsCount()).thenReturn(3);
        Mockito.when(parkingLotMock3.getParkedCarsCount()).thenReturn(1);

        Attendant attendant = new Attendant(parkingLotSelector);
        attendant.addParkingLot(parkingLotMock);
        attendant.addParkingLot(parkingLotMock2);
        attendant.addParkingLot(parkingLotMock3);

        Car carMock = Mockito.mock(Car.class);

        Mockito.when(parkingLotMock3.isFull()).thenReturn(false);

        attendant.park(carMock);
        Mockito.verify(parkingLotMock3, Mockito.times(1)).park(carMock);
    }

    @Test
    void attendantShouldBeAbleToParkInLotWithMostCapacity() throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMock = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock2 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMock3 = Mockito.mock(ParkingLot.class);

        ParkingLotSelector parkingLotSelector = new MostCapacityParkingLotSelector();

        Mockito.when(parkingLotMock.getCapacity()).thenReturn(2);
        Mockito.when(parkingLotMock2.getCapacity()).thenReturn(1);
        Mockito.when(parkingLotMock3.getCapacity()).thenReturn(3);

        Attendant attendant = new Attendant(parkingLotSelector);
        attendant.addParkingLot(parkingLotMock);
        attendant.addParkingLot(parkingLotMock2);
        attendant.addParkingLot(parkingLotMock3);

        Car carMock = Mockito.mock(Car.class);

        Mockito.when(parkingLotMock3.isFull()).thenReturn(false);

        attendant.park(carMock);
        Mockito.verify(parkingLotMock3, Mockito.times(1)).park(carMock);
    }

    @Test
    void attendantShouldBeAbleToParkTheCarInCheapestLotAvailable() throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLotMocks = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMocks2 = Mockito.mock(ParkingLot.class);
        ParkingLot parkingLotMocks3 = Mockito.mock(ParkingLot.class);

        ParkingLotSelector parkingLotSelector = new CheapeastParkingLotSelector();

        Mockito.when(parkingLotMocks.getPrice()).thenReturn(10);
        Mockito.when(parkingLotMocks2.getPrice()).thenReturn(5);
        Mockito.when(parkingLotMocks3.getPrice()).thenReturn(2);

        Attendant attendant = new Attendant(parkingLotSelector);
        attendant.addParkingLot(parkingLotMocks);
        attendant.addParkingLot(parkingLotMocks2);
        attendant.addParkingLot(parkingLotMocks3);

        Car carMock = Mockito.mock(Car.class);
        Mockito.when(parkingLotMocks3.isFull()).thenReturn(false);
        attendant.park(carMock);
        Mockito.verify(parkingLotMocks3,Mockito.times(1)).park(carMock);
    }
}

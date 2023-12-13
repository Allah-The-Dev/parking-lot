package parking.lot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

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

    @Test
    void shouldBeAbleToUnpark()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        parkingLot.park(car);
        parkingLot.unpark(car);
    }

    @Test
    void shouldThrowCarNotParkedExceptionForNotParkedCar()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        Car car2 = new Car();
        parkingLot.park(car);
        parkingLot.unpark(car);
        assertThrows(CarNotParkedException.class, () -> parkingLot.unpark(car2));

    }

    @Test
    void shouldReturnTrueWhenCarIsAvailableToBeUnparked()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        parkingLot.park(car);

        assertTrue(parkingLot.isParked(car));

    }

    @Test
    void shouldReturnFalseWhenCarIsNotAvailableToBeUnparked()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLot = new ParkingLot(1);

        Car car = new Car();
        parkingLot.park(car);

        assertFalse(parkingLot.isParked(new Car()));
    }

    @Test
    void shouldReturnCarNotParkedExceptionWhenCapacityIs0()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        ParkingLot parkingLot = new ParkingLot(0);

        assertThrows(CarNotParkedException.class, () -> parkingLot.unpark(new Car()));

    }

    @Test
    void shouldNotifyOwnerForFullParkingLot() throws ParkingLotFullException, CarAlreadyParkedException {
        Observer ownerMock = Mockito.mock(Observer.class);

        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.registerObserver(ownerMock);

        parkingLot.park(new Car());
        parkingLot.park(new Car());

        Mockito.verify(ownerMock, Mockito.times(1)).nofifyFull();
    }

    @Test
    void shouldNotifyOwnerForAvailableParkingLot()
            throws ParkingLotFullException, CarAlreadyParkedException, CarNotParkedException {
        Observer ownerMock = Mockito.mock(Observer.class);
        ParkingLot parkingLot = new ParkingLot(2);

        parkingLot.registerObserver(ownerMock);

        Car car2 = new Car();
        parkingLot.park(new Car());
        parkingLot.park(car2);
        parkingLot.unpark(car2);

        Mockito.verify(ownerMock, Mockito.times(1)).notifyAvailable();
    }

    @Test
    void shouldNotifyOwnerAndTrafficCopForFullParkingLot() throws ParkingLotFullException, CarAlreadyParkedException {
        Observer ownerMock = Mockito.mock(Observer.class);
        Observer trafficCopMock = Mockito.mock(Observer.class);

        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.registerObserver(ownerMock);
        parkingLot.registerObserver(trafficCopMock);

        parkingLot.park(new Car());
        parkingLot.park(new Car());

        Mockito.verify(ownerMock, Mockito.times(1)).nofifyFull();
        Mockito.verify(trafficCopMock, Mockito.times(1)).nofifyFull();
    }

}
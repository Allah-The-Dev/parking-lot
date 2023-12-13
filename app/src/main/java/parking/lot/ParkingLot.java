package parking.lot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    int capacity;
    Set<Car> parkedCars = new HashSet<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        if (capacity <= 0) {
            throw new ParkingLotFullException();
        }
        if (parkedCars.contains(car)) {
            throw new CarAlreadyParkedException();
        }
        parkedCars.add(car);
        capacity--;
    }

    public void unpark(Car car) throws CarNotParkedException {
        if (!parkedCars.contains(car)) {
            throw new CarNotParkedException();
        }
        capacity++;
        parkedCars.remove(car);

    }

}

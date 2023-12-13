package parking.lot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    final int capacity;
    Set<Car> parkedCars = new HashSet<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        if (isParked(car)) {
            throw new CarAlreadyParkedException();
        }
        if (capacity - parkedCars.size() <= 0) {
            throw new ParkingLotFullException();
        }

        parkedCars.add(car);

    }

    public void unpark(Car car) throws CarNotParkedException {
        if (!isParked(car)) {
            throw new CarNotParkedException();
        }

        parkedCars.remove(car);

    }

    public boolean isParked(Car car) {
        if (parkedCars.contains(car)) {
            return true;
        }
        return false;
    }

}

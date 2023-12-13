package parking.lot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    final int capacity;
    Owner owner;
    Set<Car> parkedCars = new HashSet<>();

 

    public ParkingLot(int capacity, Owner ownerMock) {
        this.capacity = capacity;
        this.owner = ownerMock;
    }

    public void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        if (isParked(car)) {
            throw new CarAlreadyParkedException();
        }
        if (capacity - parkedCars.size() <= 0) {
            throw new ParkingLotFullException();
        }

        parkedCars.add(car);
        if (capacity - parkedCars.size() == 0) {
            this.owner.nofifyFull();
        }

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

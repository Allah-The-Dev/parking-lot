package parking.lot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {
    final int capacity;
    List<Observer> observers = new ArrayList<>();
    Set<Car> parkedCars = new HashSet<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        if (isParked(car)) {
            throw new CarAlreadyParkedException();
        }
        if (capacity - parkedCars.size() <= 0) {
            throw new ParkingLotFullException();
        }

        parkedCars.add(car);
        if (capacity == parkedCars.size()) {
            this.observers.forEach(observer -> observer.nofifyFull());
        }

    }

    boolean isFull() {
        return this.capacity > this.parkedCars.size();
    }

    public void unpark(Car car) throws CarNotParkedException {
        if (!isParked(car)) {
            throw new CarNotParkedException();
        }

        parkedCars.remove(car);
        if (capacity - parkedCars.size() == 1) {
            this.observers.forEach(observer -> observer.notifyAvailable());
        }

    }

    public boolean isParked(Car car) {
        if (parkedCars.contains(car)) {
            return true;
        }
        return false;
    }

}

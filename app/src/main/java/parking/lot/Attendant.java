package parking.lot;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private final List<ParkingLot> parkingLots;
    private ParkingLotSelector parkingLotSelector;

    Attendant() {
        this.parkingLots = new ArrayList<>();
    }

    Attendant(ParkingLotSelector parkingLotSelector) {
        this.parkingLots = new ArrayList<>();
        this.parkingLotSelector = parkingLotSelector;
    }

    void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        ParkingLot parkingLot = this.parkingLotSelector.select(this.parkingLots);
        if (!parkingLot.isFull()) {
            parkingLot.park(car);
        }
    }

    public void unpark(Car car) throws CarNotParkedException {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isParked(car)) {
                parkingLot.unpark(car);
            }
        }
    }
}

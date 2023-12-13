package parking.lot;

import java.util.ArrayList;
import java.util.List;

public class Attendant {
    private final List<ParkingLot> parkingLots;

    Attendant() {
        this.parkingLots = new ArrayList<>();
    }

    void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    void park(Car car) throws ParkingLotFullException, CarAlreadyParkedException {
        this.parkingLots.get(0).park(car);
    }
}

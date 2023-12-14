package parking.lot;

import java.util.List;

public class LeastCarsParkedSelector implements ParkingLotSelector {

    @Override
    public ParkingLot select(List<ParkingLot> parkingLots) {
        ParkingLot parkingLotMinimum = new ParkingLot(0);
        if (parkingLots.size() > 0) {
            parkingLotMinimum = parkingLots.get(0);
        }

        for (int i = 1; i < parkingLots.size(); i++) {
            ParkingLot parkingLot = parkingLots.get(i);
            if (parkingLotMinimum.getParkedCarsCount() > parkingLot.getParkedCarsCount()) {
                if (!parkingLot.isFull()) {
                    parkingLotMinimum = parkingLot;
                }
            }
        }
        return parkingLotMinimum;
    }

}

package parking.lot;

import java.util.List;

public class MostCapacityParkingLotSelector implements ParkingLotSelector {

    @Override
    public ParkingLot select(List<ParkingLot> parkingLots) {
        ParkingLot maxCapacityParkingLot = new ParkingLot(0);
        if (parkingLots.size() > 0) {
            maxCapacityParkingLot = parkingLots.get(0);
        }
        for (int i = 1; i < parkingLots.size(); i++) {
            ParkingLot parkingLot = parkingLots.get(i);
            if (parkingLot.getCapacity() > maxCapacityParkingLot.getCapacity() && !parkingLot.isFull()) {
                maxCapacityParkingLot = parkingLot;
            }
        }
        return maxCapacityParkingLot;
    }

}

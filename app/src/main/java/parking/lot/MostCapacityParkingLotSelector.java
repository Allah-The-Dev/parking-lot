package parking.lot;

import java.util.Comparator;
import java.util.List;

public class MostCapacityParkingLotSelector implements ParkingLotSelector {

    @Override
    public ParkingLot select(List<ParkingLot> parkingLots) {
        return parkingLots
                .stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .max(Comparator.comparing(parkingLot -> parkingLot.getCapacity()))
                .orElse(new ParkingLot(0));
    }

}

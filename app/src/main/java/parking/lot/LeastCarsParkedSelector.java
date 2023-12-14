package parking.lot;

import java.util.Comparator;
import java.util.List;

public class LeastCarsParkedSelector implements ParkingLotSelector {

    @Override
    public ParkingLot select(List<ParkingLot> parkingLots) {
        return parkingLots
                .stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .min(Comparator.comparing(parkingLot -> parkingLot.getParkedCarsCount()))
                .orElse(new ParkingLot(0));
    }

}

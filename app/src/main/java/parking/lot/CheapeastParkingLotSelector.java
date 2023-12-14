package parking.lot;

import java.util.Comparator;
import java.util.List;

public class CheapeastParkingLotSelector implements ParkingLotSelector {

    @Override
    public ParkingLot select(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .min(Comparator.comparing(parkingLot -> parkingLot.getPrice()))
                .orElse(new ParkingLot(0));
    }

}

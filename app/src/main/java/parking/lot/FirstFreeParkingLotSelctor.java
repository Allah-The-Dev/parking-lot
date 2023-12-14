package parking.lot;

import java.util.List;

public class FirstFreeParkingLotSelctor implements ParkingLotSelector {

    @Override
    public ParkingLot select(List<ParkingLot> parkingLots) {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot;
            }
        }
        return new ParkingLot(0);
    }
    
}

package parking.lot;

import java.util.List;

public interface ParkingLotSelector {
    ParkingLot select(List<ParkingLot> parkingLots);
}

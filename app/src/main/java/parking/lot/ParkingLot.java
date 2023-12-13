package parking.lot;

public class ParkingLot {
    int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void park(Car car) throws ParkingLotFullException {
        if (capacity <= 0) {
            throw new ParkingLotFullException();
        }
        capacity--;
    }

}

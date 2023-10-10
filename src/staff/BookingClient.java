package staff;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import hotel.BookingDetail;
import hotel.BookingManager;
import hotel.IBookingManager;

public class BookingClient extends AbstractScriptedSimpleTest {

    private IBookingManager bm = null;

    public static void main(String[] args) throws Exception {
        BookingClient client = new BookingClient();
        client.run();
    }

    /***************
     * CONSTRUCTOR *
     ***************/
    public BookingClient() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            bm = (IBookingManager) registry.lookup("BookingManager");
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
        //Implement this method
        return true;
    }

    @Override
    public void addBooking(BookingDetail bookingDetail) {
        //Implement this method
    }

    @Override
    public Set<Integer> getAvailableRooms(LocalDate date) {
        //Implement this method
        return null;
    }

    @Override
    public Set<Integer> getAllRooms() throws RemoteException {
        return bm.getAllRooms();
    }
}

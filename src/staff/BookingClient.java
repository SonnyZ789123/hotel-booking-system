package staff;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import hotel.BookingDetail;
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
        try {
            return bm.isRoomAvailable(roomNumber, date);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void addBooking(BookingDetail bookingDetail) {
        try {
            bm.addBooking(bookingDetail);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public Set<Integer> getAvailableRooms(LocalDate date) {
        try {
            return bm.getAvailableRooms(date);
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    @Override
    public Set<Integer> getAllRooms() {
        try {
            return bm.getAllRooms();
        } catch (RemoteException e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
            return new HashSet<>();
        }
    }
}

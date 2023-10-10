package staff;

import hotel.BookingManager;
import hotel.IBookingManager;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
public class BookingServer {
    public static void main(String[] args) {
        try {
            BookingManager bookingManager = new BookingManager();
            IBookingManager stub = (IBookingManager) UnicastRemoteObject.exportObject(bookingManager, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("BookingManager", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

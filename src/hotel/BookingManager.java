package hotel;

import java.time.LocalDate;
import java.util.*;

public class BookingManager implements IBookingManager {

    private Room[] rooms;

    public BookingManager() {
        this.rooms = initializeRooms();
    }

    @Override
    public Set<Integer> getAllRooms() {
        Set<Integer> allRooms = new HashSet<Integer>();
        Iterable<Room> roomIterator = Arrays.asList(rooms);
        for (Room room : roomIterator) {
            allRooms.add(room.getRoomNumber());
        }
        return allRooms;
    }

    @Override
    public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
        Room room = getRoom(roomNumber);
        if (room == null) return false;

        List<BookingDetail> bookingDetails = room.getBookings();
        Optional<BookingDetail> bookingDetailOptional = bookingDetails.stream()
                .filter(bookingDetail -> bookingDetail.getDate().equals(date)).findFirst();
        return bookingDetailOptional.isEmpty();
    }

    @Override
    public void addBooking(BookingDetail bookingDetail) {
        //implement this method
    }

    @Override
    public Set<Integer> getAvailableRooms(LocalDate date) {
        //implement this method
        return null;
    }

    private Room getRoom(Integer roomNumber) {
        Optional<Room> roomOptional = Arrays.stream(rooms)
                .filter(room -> room.getRoomNumber().equals(roomNumber)).findFirst();
        return roomOptional.orElse(null);
    }

    private static Room[] initializeRooms() {
        Room[] rooms = new Room[4];
        rooms[0] = new Room(101);
        rooms[1] = new Room(102);
        rooms[2] = new Room(201);
        rooms[3] = new Room(203);
        return rooms;
    }
}

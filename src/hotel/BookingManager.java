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
    public synchronized void addBooking(BookingDetail bookingDetail) {
        Integer roomNumber = bookingDetail.getRoomNumber();
        // TODO: also ask if this is what they meant to make this thread-safe
        // TODO: this check triggers an error "Room 102 is not available on 2023-10-10" by the AbstractScriptedSimpleTest that I can't change.
        if (!isRoomAvailable(roomNumber, bookingDetail.getDate())) {
            throw new IllegalArgumentException(
                    "Room " + roomNumber + " is not available on " + bookingDetail.getDate().toString());
        }
        Room room = getRoom(roomNumber);
        room.addBooking(bookingDetail);
    }

    @Override
    public Set<Integer> getAvailableRooms(LocalDate date) {
        Set<Integer> availableRooms = new HashSet<>();

        for (Room room : rooms) {
            if (isRoomAvailable(room.getRoomNumber(), date)) {
                availableRooms.add(room.getRoomNumber());
            }
        }
        return availableRooms;
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

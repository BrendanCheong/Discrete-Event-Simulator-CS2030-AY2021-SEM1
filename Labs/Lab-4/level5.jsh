import java.util.PriorityQueue;

public Booking findBestBooking(Request request, Driver[] drivers) {
    PriorityQueue<Booking> bookingQueue = new PriorityQueue<>(100,
        (booking1, booking2) -> booking1.compareTo(booking2));
    for (Driver driver : drivers) {
        Booking booking = new Booking(driver, request);
        bookingQueue.add(booking);
    }
    return bookingQueue.peek();
}

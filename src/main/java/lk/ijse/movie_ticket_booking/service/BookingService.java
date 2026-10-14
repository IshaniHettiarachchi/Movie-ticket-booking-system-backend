package lk.ijse.movie_ticket_booking.service;

import lk.ijse.movie_ticket_booking.entity.Booking;
import lk.ijse.movie_ticket_booking.entity.BookingStatus;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(Long id);

    List<Booking> getBookingsByUser(Long userId);

    List<Booking> getBookingsByShow(Long showId);

    Booking updateBookingStatus(Long id, BookingStatus status);

    Booking cancelBooking(Long id);
}

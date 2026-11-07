package lk.ijse.movie_ticket_booking.controller;

import lk.ijse.movie_ticket_booking.entity.Booking;
import lk.ijse.movie_ticket_booking.entity.BookingStatus;
import lk.ijse.movie_ticket_booking.service.BookingService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@Valid @RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @GetMapping("/show/{showId}")
    public List<Booking> getBookingsByShow(@PathVariable Long showId) {
        return bookingService.getBookingsByShow(showId);
    }

    @PatchMapping("/{id}/status")
    public Booking updateBookingStatus(
            @PathVariable Long id,
            @RequestParam BookingStatus status) {
        return bookingService.updateBookingStatus(id, status);
    }

    @PatchMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}

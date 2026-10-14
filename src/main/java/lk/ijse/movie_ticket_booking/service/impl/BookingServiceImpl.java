package lk.ijse.movie_ticket_booking.service.impl;

import lk.ijse.movie_ticket_booking.entity.Booking;
import lk.ijse.movie_ticket_booking.entity.BookingStatus;
import lk.ijse.movie_ticket_booking.repository.BookingRepository;
import lk.ijse.movie_ticket_booking.repository.ShowRepository;
import lk.ijse.movie_ticket_booking.repository.UserRepository;
import lk.ijse.movie_ticket_booking.service.BookingService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    public BookingServiceImpl(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            ShowRepository showRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {

        if (booking.getUser() == null
                || booking.getUser().getId() == null
                || !userRepository.existsById(booking.getUser().getId())) {
            throw new IllegalArgumentException("A valid user is required");
        }

        if (booking.getShow() == null
                || booking.getShow().getId() == null) {
            throw new IllegalArgumentException("Show ID is required");
        }

        var show = showRepository.findById(booking.getShow().getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Show not found"));

        if (show.getTicketPrice() == null
                || show.getTicketPrice().signum() <= 0) {
            throw new IllegalArgumentException("Invalid ticket price");
        }

        if (booking.getSeatNumbers() == null
                || booking.getSeatNumbers().isBlank()) {
            throw new IllegalArgumentException("Seat numbers are required");
        }

        List<String> seats = Arrays.stream(
                        booking.getSeatNumbers().split(","))
                .map(String::trim)
                .filter(seat -> !seat.isEmpty())
                .toList();

        if (seats.isEmpty()
                || seats.stream().distinct().count() != seats.size()) {
            throw new IllegalArgumentException(
                    "Select valid, non-duplicate seat numbers");
        }

        if (booking.getNumberOfTickets() == null
                || booking.getNumberOfTickets() != seats.size()) {
            throw new IllegalArgumentException(
                    "Ticket count must match the number of seats");
        }

        booking.setId(null);
        booking.setShow(show);
        booking.setSeatNumbers(String.join(",", seats));
        booking.setTotalAmount(show.getTicketPrice()
                .multiply(BigDecimal.valueOf(seats.size())));
        booking.setStatus(BookingStatus.PENDING);

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getBookingsByShow(Long showId) {
        return bookingRepository.findByShowId(showId);
    }

    @Override
    public Booking updateBookingStatus(Long id, BookingStatus status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking cancelBooking(Long id) {
        Booking booking = getBookingById(id);

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new IllegalArgumentException(
                    "Booking is already cancelled");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
}

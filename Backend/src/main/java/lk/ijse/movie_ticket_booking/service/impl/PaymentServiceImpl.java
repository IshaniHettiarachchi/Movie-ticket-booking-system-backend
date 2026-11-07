package lk.ijse.movie_ticket_booking.service.impl;

import lk.ijse.movie_ticket_booking.entity.BookingStatus;
import lk.ijse.movie_ticket_booking.entity.Payment;
import lk.ijse.movie_ticket_booking.entity.PaymentStatus;
import lk.ijse.movie_ticket_booking.repository.BookingRepository;
import lk.ijse.movie_ticket_booking.repository.PaymentRepository;
import lk.ijse.movie_ticket_booking.service.PaymentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Payment createPayment(Payment payment) {

        if (payment.getBooking() == null
                || payment.getBooking().getId() == null) {
            throw new IllegalArgumentException("Booking ID is required");
        }

        var booking = bookingRepository.findById(
                        payment.getBooking().getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Booking not found"));

        if (paymentRepository.findByBookingId(booking.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    "A payment already exists for this booking");
        }

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new IllegalArgumentException(
                    "Only pending bookings can be paid");
        }

        payment.setId(null);
        payment.setBooking(booking);

        // Always use the server-calculated booking amount.
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.PENDING);

        return paymentRepository.save(payment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found for booking: " + bookingId));
    }

    @Override
    public Payment updatePaymentStatus(Long id, PaymentStatus status) {

        Payment payment = getPaymentById(id);

        if (payment.getStatus() == PaymentStatus.COMPLETED
                || payment.getStatus() == PaymentStatus.REFUNDED) {
            throw new IllegalArgumentException(
                    "This payment status cannot be changed directly");
        }

        payment.setStatus(status);

        if (status == PaymentStatus.COMPLETED) {
            var booking = payment.getBooking();
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
        }

        if (status == PaymentStatus.FAILED) {
            payment.getBooking().setStatus(BookingStatus.PENDING);
        }

        return paymentRepository.save(payment);
    }}

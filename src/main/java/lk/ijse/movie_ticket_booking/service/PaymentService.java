package lk.ijse.movie_ticket_booking.service;

import lk.ijse.movie_ticket_booking.entity.Payment;
import lk.ijse.movie_ticket_booking.entity.PaymentStatus;

import java.util.List;

public interface PaymentService {

    Payment createPayment(Payment payment);

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    Payment getPaymentByBooking(Long bookingId);

    Payment updatePaymentStatus(Long id, PaymentStatus status);
}

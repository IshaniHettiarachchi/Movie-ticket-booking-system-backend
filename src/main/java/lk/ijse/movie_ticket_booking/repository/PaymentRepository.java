package lk.ijse.movie_ticket_booking.repository;

import lk.ijse.movie_ticket_booking.entity.Payment;
import lk.ijse.movie_ticket_booking.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingId(Long bookingId);

    List<Payment> findByStatus(PaymentStatus status);
}

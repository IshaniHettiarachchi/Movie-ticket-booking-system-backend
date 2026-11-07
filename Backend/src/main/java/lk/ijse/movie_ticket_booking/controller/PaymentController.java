package lk.ijse.movie_ticket_booking.controller;

import lk.ijse.movie_ticket_booking.entity.Payment;
import lk.ijse.movie_ticket_booking.entity.PaymentStatus;
import lk.ijse.movie_ticket_booking.service.PaymentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment createPayment(@Valid @RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/booking/{bookingId}")
    public Payment getPaymentByBooking(@PathVariable Long bookingId) {
        return paymentService.getPaymentByBooking(bookingId);
    }

    @PatchMapping("/{id}/status")
    public Payment updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam PaymentStatus status) {
        return paymentService.updatePaymentStatus(id, status);
    }
}

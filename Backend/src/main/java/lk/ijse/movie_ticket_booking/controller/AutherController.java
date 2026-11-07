package lk.ijse.movie_ticket_booking.controller;

import jakarta.validation.Valid;

import lk.ijse.movie_ticket_booking.dto.AutherResponse;
import lk.ijse.movie_ticket_booking.dto.LoginRequest;
import lk.ijse.movie_ticket_booking.dto.RegisterRequest;
import lk.ijse.movie_ticket_booking.security.AutherService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AutherController {

    private final AutherService autherService;

    public AutherController(AutherService autherService) {
        this.autherService = autherService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AutherResponse register(
            @Valid @RequestBody RegisterRequest request) {
        return autherService.register(request);
    }

    @PostMapping("/login")
    public AutherResponse login(
            @Valid @RequestBody LoginRequest request) {
        return autherService.login(request);
    }
}
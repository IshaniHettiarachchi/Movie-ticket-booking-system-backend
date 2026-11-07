package lk.ijse.movie_ticket_booking.controller;

import jakarta.validation.Valid;

import lk.ijse.movie_ticket_booking.dto.AutherResponse;
import lk.ijse.movie_ticket_booking.dto.LoginRequest;
import lk.ijse.movie_ticket_booking.dto.RegisterRequest;
import lk.ijse.movie_ticket_booking.security.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AutherResponse register(
            @Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AutherResponse login(
            @Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
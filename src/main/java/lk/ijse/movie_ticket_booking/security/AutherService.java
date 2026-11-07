package lk.ijse.movie_ticket_booking.security;

import lk.ijse.movie_ticket_booking.dto.AuthResponse;
import lk.ijse.movie_ticket_booking.dto.LoginRequest;
import lk.ijse.movie_ticket_booking.dto.RegisterRequest;
import lk.ijse.movie_ticket_booking.entity.Role;
import lk.ijse.movie_ticket_booking.entity.User;
import lk.ijse.movie_ticket_booking.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "An account with this email already exists");
        }

        User user = new User();
        user.setFullName(request.getName().trim());
        user.setEmail(email);
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));

        // Public registration must never grant administrator privileges.
        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser.getEmail());

        return new AuthResponse(
                token,
                savedUser.getEmail(),
                savedUser.getRole().name());
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getRole().name());
    }
}






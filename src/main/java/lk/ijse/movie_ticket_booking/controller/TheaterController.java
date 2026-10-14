package lk.ijse.movie_ticket_booking.controller;

import lk.ijse.movie_ticket_booking.entity.Theatre;
import lk.ijse.movie_ticket_booking.entity.TheatreStatus;
import lk.ijse.movie_ticket_booking.service.TheatreService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@CrossOrigin(origins = "http://localhost:5173")
public class TheaterController {

    private final TheatreService theatreService;

    public TheaterController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Theatre createTheatre(@Valid @RequestBody Theatre theatre) {
        return theatreService.createTheatre(theatre);
    }

    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable Long id) {
        return theatreService.getTheatreById(id);
    }

    @PutMapping("/{id}")
    public Theatre updateTheatre(
            @PathVariable Long id,
            @Valid @RequestBody Theatre theatre) {
        return theatreService.updateTheatre(id, theatre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
    }

    @GetMapping("/status/{status}")
    public List<Theatre> getTheatresByStatus(
            @PathVariable TheatreStatus status) {
        return theatreService.getTheatresByStatus(status);
    }
}
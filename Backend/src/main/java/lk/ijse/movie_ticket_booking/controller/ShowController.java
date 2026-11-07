package lk.ijse.movie_ticket_booking.controller;

import lk.ijse.movie_ticket_booking.entity.Show;
import lk.ijse.movie_ticket_booking.entity.ShowStatus;
import lk.ijse.movie_ticket_booking.service.ShowService;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "http://localhost:5173")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Show createShow(@Valid @RequestBody Show show) {
        return showService.createShow(show);
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    @PutMapping("/{id}")
    public Show updateShow(
            @PathVariable Long id,
            @Valid @RequestBody Show show) {
        return showService.updateShow(id, show);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
    }

    @GetMapping("/movie/{movieId}")
    public List<Show> getShowsByMovie(@PathVariable Long movieId) {
        return showService.getShowsByMovie(movieId);
    }

    @GetMapping("/theatre/{theatreId}")
    public List<Show> getShowsByTheatre(@PathVariable Long theatreId) {
        return showService.getShowsByTheatre(theatreId);
    }

    @GetMapping("/date/{date}")
    public List<Show> getShowsByDate(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return showService.getShowsByDate(date);
    }

    @GetMapping("/status/{status}")
    public List<Show> getShowsByStatus(@PathVariable ShowStatus status) {
        return showService.getShowsByStatus(status);
    }

}

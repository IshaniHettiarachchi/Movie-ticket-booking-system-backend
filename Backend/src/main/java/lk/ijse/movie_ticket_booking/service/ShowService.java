package lk.ijse.movie_ticket_booking.service;

import lk.ijse.movie_ticket_booking.entity.Show;
import lk.ijse.movie_ticket_booking.entity.ShowStatus;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {

    Show createShow(Show show);

    List<Show> getAllShows();

    Show getShowById(Long id);

    Show updateShow(Long id, Show show);

    void deleteShow(Long id);

    List<Show> getShowsByMovie(Long movieId);

    List<Show> getShowsByTheatre(Long theatreId);

    List<Show> getShowsByDate(LocalDate showDate);

    List<Show> getShowsByStatus(ShowStatus status);
}

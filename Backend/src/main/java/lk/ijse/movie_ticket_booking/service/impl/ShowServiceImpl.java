package lk.ijse.movie_ticket_booking.service.impl;

import lk.ijse.movie_ticket_booking.entity.Show;
import lk.ijse.movie_ticket_booking.entity.ShowStatus;
import lk.ijse.movie_ticket_booking.repository.ShowRepository;
import lk.ijse.movie_ticket_booking.service.ShowService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show createShow(Show show) {
        show.setId(null);

        if (show.getMovie() == null || show.getMovie().getId() == null) {
            throw new IllegalArgumentException("Movie ID is required");
        }

        if (show.getTheatre() == null || show.getTheatre().getId() == null) {
            throw new IllegalArgumentException("Theatre ID is required");
        }

        if (show.getShowDate() == null || show.getShowTime() == null) {
            throw new IllegalArgumentException("Show date and time are required");
        }

        if (show.getTicketPrice() == null
                || show.getTicketPrice().signum() <= 0) {
            throw new IllegalArgumentException(
                    "Ticket price must be greater than zero");
        }

        return showRepository.save(show);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Show not found with ID: " + id));
    }

    @Override
    public Show updateShow(Long id, Show show) {
        Show existingShow = getShowById(id);

        if (show.getMovie() == null || show.getMovie().getId() == null) {
            throw new IllegalArgumentException("Movie ID is required");
        }

        if (show.getTheatre() == null || show.getTheatre().getId() == null) {
            throw new IllegalArgumentException("Theatre ID is required");
        }

        if (show.getShowDate() == null || show.getShowTime() == null) {
            throw new IllegalArgumentException("Show date and time are required");
        }

        if (show.getTicketPrice() == null
                || show.getTicketPrice().signum() <= 0) {
            throw new IllegalArgumentException(
                    "Ticket price must be greater than zero");
        }

        existingShow.setMovie(show.getMovie());
        existingShow.setTheatre(show.getTheatre());
        existingShow.setShowDate(show.getShowDate());
        existingShow.setShowTime(show.getShowTime());
        existingShow.setTicketPrice(show.getTicketPrice());
        existingShow.setStatus(show.getStatus());

        return showRepository.save(existingShow);
    }

    @Override
    public void deleteShow(Long id) {
        Show existingShow = getShowById(id);
        showRepository.delete(existingShow);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getShowsByTheatre(Long theatreId) {
        return showRepository.findByTheatreId(theatreId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getShowsByDate(LocalDate showDate) {
        return showRepository.findByShowDate(showDate);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Show> getShowsByStatus(ShowStatus status) {
        return showRepository.findByStatus(status);
    }
}

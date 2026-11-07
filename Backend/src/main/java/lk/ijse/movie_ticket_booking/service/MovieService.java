package lk.ijse.movie_ticket_booking.service;

import lk.ijse.movie_ticket_booking.entity.Movie;
import lk.ijse.movie_ticket_booking.entity.MovieStatus;

import java.util.List;
public interface MovieService {

    Movie createMovie(Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);

    Movie updateMovie(Long id, Movie movie);

    void deleteMovie(Long id);

    List<Movie> getMoviesByStatus(MovieStatus status);
}

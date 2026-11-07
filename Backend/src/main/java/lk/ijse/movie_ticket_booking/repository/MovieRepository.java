package lk.ijse.movie_ticket_booking.repository;

import lk.ijse.movie_ticket_booking.entity.Movie;
import lk.ijse.movie_ticket_booking.entity.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByStatus(MovieStatus status);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByLanguageIgnoreCase(String language);
}


package lk.ijse.movie_ticket_booking.repository;

import lk.ijse.movie_ticket_booking.entity.Show;
import lk.ijse.movie_ticket_booking.entity.ShowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieId(Long movieId);

    List<Show> findByTheatreId(Long theatreId);

    List<Show> findByShowDate(LocalDate showDate);

    List<Show> findByStatus(ShowStatus status);
}

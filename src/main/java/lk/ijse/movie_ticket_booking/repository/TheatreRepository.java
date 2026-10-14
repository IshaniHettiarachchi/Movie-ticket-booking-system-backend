
package lk.ijse.movie_ticket_booking.repository;

import lk.ijse.movie_ticket_booking.entity.Theatre;
import lk.ijse.movie_ticket_booking.entity.TheatreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository
        extends JpaRepository<Theatre, Long> {

    List<Theatre> findByStatus(TheatreStatus status);
}

package lk.ijse.movie_ticket_booking.service;


import lk.ijse.movie_ticket_booking.entity.Theatre;
import lk.ijse.movie_ticket_booking.entity.TheatreStatus;

import java.util.List;

public interface TheatreService {

    Theatre createTheatre(Theatre theatre);

    List<Theatre> getAllTheatres();

    Theatre getTheatreById(Long id);

    Theatre updateTheatre(Long id, Theatre theatre);

    void deleteTheatre(Long id);

    List<Theatre> getTheatresByStatus(TheatreStatus status);
}

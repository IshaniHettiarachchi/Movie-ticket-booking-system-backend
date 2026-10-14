package lk.ijse.movie_ticket_booking.service.impl;

import lk.ijse.movie_ticket_booking.entity.Theatre;
import lk.ijse.movie_ticket_booking.entity.TheatreStatus;
import lk.ijse.movie_ticket_booking.repository.TheatreRepository;
import lk.ijse.movie_ticket_booking.service.TheatreService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public Theatre createTheatre(Theatre theatre) {
        theatre.setId(null);
        return theatreRepository.save(theatre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Theatre not found with ID: " + id));
    }

    @Override
    public Theatre updateTheatre(Long id, Theatre theatre) {
        Theatre existingTheatre = getTheatreById(id);

        existingTheatre.setName(theatre.getName());
        existingTheatre.setLocation(theatre.getLocation());
        existingTheatre.setCapacity(theatre.getCapacity());
        existingTheatre.setStatus(theatre.getStatus());

        return theatreRepository.save(existingTheatre);
    }

    @Override
    public void deleteTheatre(Long id) {
        Theatre existingTheatre = getTheatreById(id);
        theatreRepository.delete(existingTheatre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Theatre> getTheatresByStatus(TheatreStatus status) {
        return theatreRepository.findByStatus(status);
    }
}
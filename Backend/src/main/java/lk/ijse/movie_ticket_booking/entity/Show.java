package lk.ijse.movie_ticket_booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "movie_shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Movie is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull(message = "Theatre is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @NotNull(message = "Show date is required")
    @Column(nullable = false)
    private LocalDate showDate;

    @NotNull(message = "Show time is required")
    @Column(nullable = false)
    private LocalTime showTime;

    @NotNull(message = "Ticket price is required")
    @DecimalMin(value = "0.01", message = "Ticket price must be positive")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ShowStatus status = ShowStatus.SCHEDULED;

    public Show() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalTime showTime) {
        this.showTime = showTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public ShowStatus getStatus() {
        return status;
    }

    public void setStatus(ShowStatus status) {
        this.status = status;
    }
}

package lk.ijse.movie_ticket_booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Movie title is required")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be positive")
    @Column(nullable = false)
    private Integer duration;

    @NotBlank(message = "Language is required")
    @Column(nullable = false, length = 50)
    private String language;

    @NotBlank(message = "Genre is required")
    @Column(nullable = false, length = 50)
    private String genre;

    @NotNull(message = "Release date is required")
    @Column(nullable = false)
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MovieStatus status = MovieStatus.UPCOMING;

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }
}

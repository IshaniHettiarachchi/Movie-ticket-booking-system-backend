package lk.ijse.movie_ticket_booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "theatres")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Theatre name is required")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Location is required")
    @Column(nullable = false, length = 200)
    private String location;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be positive")
    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TheatreStatus status = TheatreStatus.ACTIVE;

    public Theatre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public TheatreStatus getStatus() {
        return status;
    }

    public void setStatus(TheatreStatus status) {
        this.status = status;
    }
}

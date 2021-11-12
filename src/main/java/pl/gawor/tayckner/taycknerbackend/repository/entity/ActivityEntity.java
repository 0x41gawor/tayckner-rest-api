package pl.gawor.tayckner.taycknerbackend.repository.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entity class for `Activity`.
 *
 * A simple POJO class with Hibernate annotations.
 */
@Entity
@Table(name = "activity")
public class ActivityEntity {
// -------------------------------------------------------------------------------------- F I E L D S
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Column(name = "duration")
    private int duration;
    @Column(name = "breaks", nullable = false)
    private int breaks;

// -------------------------------------------------------------------------------------- C O N S T R U C T O R S
    public ActivityEntity() {
    }

    public ActivityEntity(long id, String name, CategoryEntity category, LocalDateTime startTime, LocalDateTime endTime, int duration, int breaks) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.breaks = breaks;
    }

// -------------------------------------------------------------------------------------- G E T T E R S / S E T T E R S
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBreaks() {
        return breaks;
    }

    public void setBreaks(int breaks) {
        this.breaks = breaks;
    }

// -------------------------------------------------------------------------------------- T O  S T R I N G
    @Override
    public String toString() {
        return "ActivityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", breaks=" + breaks +
                '}';
    }
}

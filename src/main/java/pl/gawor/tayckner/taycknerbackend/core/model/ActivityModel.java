package pl.gawor.tayckner.taycknerbackend.core.model;

import java.time.LocalDateTime;

/**
 * Model class for `Activity`.
 *
 * A simple POJO.
 */
public class ActivityModel {

// -------------------------------------------------------------------------------------- F I E L D S

    private long id;
    private String name;
    private CategoryModel category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private int breaks;

// -------------------------------------------------------------------------------------- C O N S T R U C T O R S

    public ActivityModel(long id, String name, CategoryModel category, LocalDateTime startTime, LocalDateTime endTime, int duration, int breaks) {
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
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
        return "ActivityModel{" +
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

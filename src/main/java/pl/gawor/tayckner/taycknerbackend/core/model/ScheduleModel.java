package pl.gawor.tayckner.taycknerbackend.core.model;


import java.time.LocalDateTime;

/**
 * Model class for `Schedule`.
 *
 * A simple POJO.
 */
public class ScheduleModel {

// -------------------------------------------------------------------------------------- F I E L D S

    private long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int duration;
    private UserModel user;

// -------------------------------------------------------------------------------------- C O N S T R U C T O R S

    public ScheduleModel(long id, String name, LocalDateTime startTime, LocalDateTime endTime, int duration, UserModel user) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.user = user;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

// -------------------------------------------------------------------------------------- T O  S T R I N G

    @Override
    public String toString() {
        return "ScheduleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", user=" + user +
                '}';
    }

}

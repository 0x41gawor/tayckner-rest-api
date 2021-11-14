package pl.gawor.tayckner.taycknerbackend.core.model;

import java.time.LocalDate;

/**
 * Model class for `HabitEvent`.
 *
 * A simple POJO.
 */
public class HabitEvent {

// -------------------------------------------------------------------------------------- F I E L D S

    private long id;
    private HabitModel habit;
    private LocalDate date;
    private String comment;
    private int value;

// -------------------------------------------------------------------------------------- C O N S T R U C T O R S

    public HabitEvent(long id, HabitModel habit, LocalDate date, String comment, int value) {
        this.id = id;
        this.habit = habit;
        this.date = date;
        this.comment = comment;
        this.value = value;
    }

// -------------------------------------------------------------------------------------- G E T T E R S / S E T T E R S

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HabitModel getHabit() {
        return habit;
    }

    public void setHabit(HabitModel habit) {
        this.habit = habit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

// -------------------------------------------------------------------------------------- T O  S T R I N G

    @Override
    public String toString() {
        return "HabitEvent{" +
                "id=" + id +
                ", habit=" + habit +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", value=" + value +
                '}';
    }

}

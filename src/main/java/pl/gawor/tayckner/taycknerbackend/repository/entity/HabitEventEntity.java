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
import java.time.LocalDate;

/**
 * Entity class for `HabitEvent`.
 *
 * A simple POJO class with Hibernate annotations.
 */
@Entity
@Table(name = "habit_event")
public class HabitEventEntity {
// -------------------------------------------------------------------------------------- F I E L D S
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private long id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "habit_id", nullable = false)
    private HabitEntity habit;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "comment", nullable = true)
    private String comment;
    @Column(name = "value", nullable = false)
    private int value;
// -------------------------------------------------------------------------------------- C O N S T R U C T O R S
    public HabitEventEntity() {
        // required by Hibernate
    }

    public HabitEventEntity(long id, HabitEntity habit, LocalDate date, String comment, int value) {
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

    public HabitEntity getHabit() {
        return habit;
    }

    public void setHabit(HabitEntity habit) {
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
        return "HabitEventEntity{" +
                "id=" + id +
                ", habit=" + habit +
                ", date=" + date +
                ", comment='" + comment + '\'' +
                ", value=" + value +
                '}';
    }
}

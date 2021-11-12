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

/**
 * Entity class for `Habit`.
 *
 * A simple POJO class with Hibernate annotations.
 */
@Entity
@Table(name = "habit")
public class HabitEntity {
// -------------------------------------------------------------------------------------- F I E L D S
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "color", nullable = false)
    private String color;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

// -------------------------------------------------------------------------------------- C O N S T R U C T O R S
    public HabitEntity() {
        // required by Hibernate
    }

    public HabitEntity(long id, String name, String color, UserEntity user) {
        this.id = id;
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

// -------------------------------------------------------------------------------------- T O  S T R I N G
    @Override
    public String toString() {
        return "HabitEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", user=" + user +
                '}';
    }
}

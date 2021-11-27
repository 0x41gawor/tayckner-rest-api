package pl.gawor.tayckner.taycknerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.List;

/**
 * Repository class for `Habit`.
 */
@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {
    List<HabitEntity> findHabitEntitiesByUser(UserEntity user);

    boolean existsByNameAndUser(String name, UserEntity user);

    boolean existsByIdAndUser(long id, UserEntity user);

    HabitEntity findHabitEntityByNameAndUser(String name, UserEntity user);
}

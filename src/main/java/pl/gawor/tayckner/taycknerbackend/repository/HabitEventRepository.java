package pl.gawor.tayckner.taycknerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEventEntity;

import java.util.List;

/**
 * Repository class for `HabitEvent`.
 *
 */
@Repository
public interface HabitEventRepository extends JpaRepository<HabitEventEntity, Long> {
    List<HabitEventEntity> findHabitEventEntitiesByHabit(HabitEntity habit);
}

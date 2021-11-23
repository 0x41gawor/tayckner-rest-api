package pl.gawor.tayckner.taycknerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ScheduleEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.List;

/**
 * Repository class for `Schedule`.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findScheduleEntitiesByUser(UserEntity user);

    boolean existsByNameAndUser(String name, UserEntity user);

    boolean existsByIdAndUser(long id, UserEntity user);
}

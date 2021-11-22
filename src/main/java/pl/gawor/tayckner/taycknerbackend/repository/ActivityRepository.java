package pl.gawor.tayckner.taycknerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ActivityEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;

import java.util.List;

/**
 * Repository class for `Activity`.
 *
 */
@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
    List<ActivityEntity> findActivityEntitiesByCategory(CategoryEntity category);
    boolean existsById(long id);
}

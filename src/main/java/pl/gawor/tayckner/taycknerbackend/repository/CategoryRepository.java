package pl.gawor.tayckner.taycknerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.List;

/**
 * Repository class for `Category`.
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findCategoryEntitiesByUser(UserEntity user);
    boolean existsByNameAndUser(String name, UserEntity user);
}

package pl.gawor.tayckner.taycknerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

/**
 * Repository class for `User`.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);
}

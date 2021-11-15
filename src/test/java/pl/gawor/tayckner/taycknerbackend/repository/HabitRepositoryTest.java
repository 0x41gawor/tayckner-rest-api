package pl.gawor.tayckner.taycknerbackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class HabitRepositoryTest {
    @Autowired private HabitRepository repository;
    @Autowired private UserRepository userRepository;

    @Test
    public void _givenKnowUser_whenFindByUser_ThenFoundHabitNotEmpty() {
        // given
        String username = "test_user";
        UserEntity user = userRepository.findUserEntityByUsername(username);

        // when
        List<HabitEntity> foundHabits = repository.findHabitEntitiesByUser(user);

        // then
        assert !foundHabits.isEmpty();
    }
}
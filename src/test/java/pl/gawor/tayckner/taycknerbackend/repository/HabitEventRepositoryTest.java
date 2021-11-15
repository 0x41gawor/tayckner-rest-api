package pl.gawor.tayckner.taycknerbackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEventEntity;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class HabitEventRepositoryTest {
    @Autowired private HabitEventRepository repository;
    @Autowired private HabitRepository habitRepository;

    @Test
    public void _givenHabit_whenFindByHabit_thenFoundHabitEventsNotEmpty() {
        // given
        HabitEntity habit = habitRepository.getById(1L);
        // when
        List<HabitEventEntity> habitEvents = repository.findHabitEventEntitiesByHabit(habit);
        // then
        assert !habitEvents.isEmpty();
    }
}
package pl.gawor.tayckner.taycknerbackend.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ActivityEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class ActivityRepositoryTest {
    @Autowired private ActivityRepository repository;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    public void _givenCategory_whenFindByCategory_thenFoundActivitiesNotEmpty() {
        // given
        CategoryEntity category = categoryRepository.getById(1L);
        // when
        List<ActivityEntity> activities = repository.findActivityEntitiesByCategory(category);
        // then
        assert !activities.isEmpty();
    }
}
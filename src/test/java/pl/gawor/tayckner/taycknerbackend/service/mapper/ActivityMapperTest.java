package pl.gawor.tayckner.taycknerbackend.service.mapper;

import org.junit.jupiter.api.Test;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ActivityEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

class ActivityMapperTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String COLOR = "color";
    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_FIRST_NAME = "test_firstName";
    private static final String TEST_LAST_NAME = "test_lastName";
    private static final String TEST_MAIL_COM = "test@mail.com";
    private static final LocalDateTime START_TIME = LocalDateTime.of(2021, Month.APRIL, 22, 12, 12, 30);
    private static final LocalDateTime END_TIME = LocalDateTime.of(2021, Month.APRIL, 22, 14, 12, 30);
    private static final int DURATION = 4;
    private static final int BREAKS = 10;

    private final ActivityMapper mapper = new ActivityMapper();

    @Test
    public void _givenEntity_whenMapToModel_thenModelFieldsEqualsEntityFields() {
        // given
        UserEntity userEntity = new UserEntity(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        CategoryEntity categoryEntity = new CategoryEntity(ID, NAME, DESCRIPTION, COLOR, userEntity);
        ActivityEntity entity = new ActivityEntity(ID, NAME, categoryEntity, START_TIME, END_TIME, DURATION, BREAKS);

        // when
        ActivityModel model = mapper.mapToModel(entity);

        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getName(), model.getName());
        assert entity.getEndTime() == model.getEndTime();
        assert entity.getStartTime() == model.getStartTime();
        assert entity.getDuration() == model.getDuration();
        assert entity.getBreaks() == model.getBreaks();
    }

    @Test
    public void _givenModel_whenMapToEntity_thenEntityFieldsEqualsModelFields() {
        // given
        UserModel userModel = new UserModel(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        CategoryModel categoryModel = new CategoryModel(ID, NAME, DESCRIPTION, COLOR, userModel);
        ActivityModel model = new ActivityModel(ID, NAME, categoryModel, START_TIME, END_TIME, DURATION, BREAKS);

        // when
        ActivityEntity entity = mapper.mapToEntity(model);

        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getName(), model.getName());
        assert entity.getEndTime() == model.getEndTime();
        assert entity.getStartTime() == model.getStartTime();
        assert entity.getDuration() == model.getDuration();
        assert entity.getBreaks() == model.getBreaks();
    }

}
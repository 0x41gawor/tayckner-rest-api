package pl.gawor.tayckner.taycknerbackend.service.mapper;

import org.junit.jupiter.api.Test;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitEventModel;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.*;

import java.time.LocalDate;
import java.util.Objects;

class HabitEventMapperTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String COMMENT = "comment";
    private static final String COLOR = "color";
    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_FIRST_NAME = "test_firstName";
    private static final String TEST_LAST_NAME = "test_lastName";
    private static final String TEST_MAIL_COM = "test@mail.com";
    private static final LocalDate DATE = LocalDate.of(2020, 1, 9);
    private static final int VALUE = 4;

    private final HabitEventMapper mapper = new HabitEventMapper();

    @Test
    public void _givenEntity_whenMapToModel_thenModelFieldsEqualsEntityFields() {
        // given
        UserEntity userEntity = new UserEntity(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        HabitEntity habitEntity = new HabitEntity(ID, NAME, COLOR, userEntity);
        HabitEventEntity entity = new HabitEventEntity(ID, habitEntity, DATE, COMMENT, VALUE);

        // when
        HabitEventModel model = mapper.mapToModel(entity);

        // then
        assert entity.getId() == model.getId();
        assert entity.getDate() == model.getDate();
        assert Objects.equals(entity.getComment(), model.getComment());
        assert entity.getValue() == model.getValue();
    }

    @Test
    public void _givenModel_whenMapToEntity_thenEntityFieldsEqualsModelFields() {
        // given
        UserModel userModel = new UserModel(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        HabitModel habitModel = new HabitModel(ID, NAME, COLOR, userModel);
        HabitEventModel model = new HabitEventModel(ID, habitModel, DATE, COMMENT, VALUE);

        // when
        HabitEventEntity entity = mapper.mapToEntity(model);

        // then
        assert model.getId() == entity.getId();
        assert model.getDate() == entity.getDate();
        assert Objects.equals(model.getComment(), entity.getComment());
        assert model.getValue() == entity.getValue();
    }

}
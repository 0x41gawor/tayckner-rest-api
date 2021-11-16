package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

import org.junit.jupiter.api.Test;
import pl.gawor.tayckner.taycknerbackend.core.model.ScheduleModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ScheduleEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

class ScheduleMapperTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_FIRST_NAME = "test_firstName";
    private static final String TEST_LAST_NAME = "test_lastName";
    private static final String TEST_MAIL_COM = "test@mail.com";
    private static final LocalDateTime START_TIME = LocalDateTime.of(2021, Month.APRIL, 22, 12, 12, 30);
    private static final LocalDateTime END_TIME = LocalDateTime.of(2021, Month.APRIL, 22, 14, 12, 30);
    private static final int DURATION = 4;

    private final ScheduleMapper mapper = new ScheduleMapper();

    @Test
    public void _givenEntity_whenMapToModel_thenModelFieldsEqualsEntityFields() {
        // given
        UserEntity userEntity = new UserEntity(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        ScheduleEntity entity = new ScheduleEntity(ID, NAME, START_TIME, END_TIME, DURATION, userEntity);

        // when
        ScheduleModel model = mapper.mapToModel(entity);
        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getName(), model.getName());
        assert entity.getStartTime() == model.getStartTime();
        assert entity.getEndTime() == model.getEndTime();
        assert entity.getDuration() == model.getDuration();

        assert entity.getUser().getId() == userEntity.getId();
        assert Objects.equals(model.getUser().getUsername(), userEntity.getUsername());
        assert Objects.equals(model.getUser().getPassword(), userEntity.getPassword());
        assert Objects.equals(model.getUser().getFirstName(), userEntity.getFirstName());
        assert Objects.equals(model.getUser().getLastName(), userEntity.getLastName());
        assert Objects.equals(model.getUser().getEmail(), userEntity.getEmail());
    }

    @Test
    public void _givenModel_whenMapToEntity_thenEntityFieldsEqualsModelFields() {
        // given
        UserModel userModel = new UserModel(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        ScheduleModel model = new ScheduleModel(ID, NAME, START_TIME, END_TIME, DURATION, userModel);

        // when
        ScheduleEntity entity = mapper.mapToEntity(model);
        // then
        assert model.getId() == entity.getId();
        assert Objects.equals(model.getName(), entity.getName());
        assert model.getStartTime() == entity.getStartTime();
        assert model.getEndTime() == entity.getEndTime();
        assert model.getDuration() == entity.getDuration();

        assert model.getUser().getId() == userModel.getId();
        assert Objects.equals(entity.getUser().getUsername(), userModel.getUsername());
        assert Objects.equals(entity.getUser().getPassword(), userModel.getPassword());
        assert Objects.equals(entity.getUser().getFirstName(), userModel.getFirstName());
        assert Objects.equals(entity.getUser().getLastName(), userModel.getLastName());
        assert Objects.equals(entity.getUser().getEmail(), userModel.getEmail());
    }

}
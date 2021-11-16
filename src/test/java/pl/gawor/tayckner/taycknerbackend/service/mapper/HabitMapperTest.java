package pl.gawor.tayckner.taycknerbackend.service.mapper;

import org.junit.jupiter.api.Test;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.Objects;

class HabitMapperTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String COLOR = "color";
    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_FIRST_NAME = "test_firstName";
    private static final String TEST_LAST_NAME = "test_lastName";
    private static final String TEST_MAIL_COM = "test@mail.com";

    private final HabitMapper mapper = new HabitMapper();

    @Test
    public void _givenEntity_whenMapToModel_thenModelFieldsEqualsEntityFields() {
        // given
        UserEntity userEntity = new UserEntity(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        HabitEntity entity = new HabitEntity(ID, NAME, COLOR, userEntity);

        // when
        HabitModel model = mapper.mapToModel(entity);

        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getName(), model.getName());
        assert Objects.equals(entity.getColor(), model.getColor());

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
        HabitModel model = new HabitModel(ID, NAME, COLOR, userModel);

        // when
        HabitEntity entity = mapper.mapToEntity(model);

        // then
        assert model.getId() == entity.getId();
        assert Objects.equals(model.getName(), entity.getName());
        assert Objects.equals(model.getColor(), entity.getColor());

        assert model.getUser().getId() == userModel.getId();
        assert Objects.equals(model.getUser().getUsername(), userModel.getUsername());
        assert Objects.equals(model.getUser().getPassword(), userModel.getPassword());
        assert Objects.equals(model.getUser().getFirstName(), userModel.getFirstName());
        assert Objects.equals(model.getUser().getLastName(), userModel.getLastName());
        assert Objects.equals(model.getUser().getEmail(), userModel.getEmail());
    }

}
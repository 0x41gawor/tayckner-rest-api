package pl.gawor.tayckner.taycknerbackend.service.mapper;

import org.junit.jupiter.api.Test;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.Objects;

class UserMapperTest {
    public static final long ID = 1L;
    public static final String TEST_USERNAME = "test_username";
    public static final String TEST_PASSWORD = "test_password";
    public static final String TEST_FIRST_NAME = "test_firstName";
    public static final String TEST_LAST_NAME = "test_lastName";
    public static final String TEST_MAIL_COM = "test@mail.com";

    private final UserMapper mapper = new UserMapper();

    @Test
    public void _givenEntity_whenMapToModel_ThenEntityFieldsEqualsModelFields() {
        // given
        UserEntity entity = new UserEntity(ID, TEST_USERNAME, TEST_PASSWORD,
                TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);

        // when
        UserModel model = mapper.mapToModel(entity);

        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getUsername(), model.getUsername());
        assert Objects.equals(entity.getPassword(), model.getPassword());
        assert Objects.equals(entity.getFirstName(), model.getFirstName());
        assert Objects.equals(entity.getLastName(), model.getLastName());
        assert Objects.equals(entity.getEmail(), model.getEmail());
    }

    @Test
    public void _givenModel_whenMapToEntity_ThenModelFieldsEqualsModelFields() {
        // given
        UserModel model = new UserModel(ID, TEST_USERNAME, TEST_PASSWORD,
                TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);

        // when
        UserEntity entity = mapper.mapToEntity(model);

        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getUsername(), model.getUsername());
        assert Objects.equals(entity.getPassword(), model.getPassword());
        assert Objects.equals(entity.getFirstName(), model.getFirstName());
        assert Objects.equals(entity.getLastName(), model.getLastName());
        assert Objects.equals(entity.getEmail(), model.getEmail());
    }

}
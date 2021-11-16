package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

import org.junit.jupiter.api.Test;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

import java.util.Objects;

class CategoryMapperTest {

    private static final long ID = 1L;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String COLOR = "color";
    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_FIRST_NAME = "test_firstName";
    private static final String TEST_LAST_NAME = "test_lastName";
    private static final String TEST_MAIL_COM = "test@mail.com";

    private final CategoryMapper mapper = new CategoryMapper();

    @Test
    public void _givenEntity_whenMapToModel_thenModelFieldsEqualsEntityFields() {
        // given
        UserEntity userEntity = new UserEntity(ID, TEST_USERNAME, TEST_PASSWORD, TEST_FIRST_NAME, TEST_LAST_NAME, TEST_MAIL_COM);
        CategoryEntity entity = new CategoryEntity(ID, NAME, DESCRIPTION, COLOR, userEntity);

        // when
        CategoryModel model = mapper.mapToModel(entity);

        // then
        assert entity.getId() == model.getId();
        assert Objects.equals(entity.getName(), model.getName());
        assert Objects.equals(entity.getDescription(), model.getDescription());
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
        CategoryModel model = new CategoryModel(ID, NAME, DESCRIPTION, COLOR, userModel);

        // when
        CategoryEntity entity = mapper.mapToEntity(model);

        // then
        assert model.getId() == entity.getId();
        assert Objects.equals(model.getName(), entity.getName());
        assert Objects.equals(model.getDescription(), entity.getDescription());
        assert Objects.equals(model.getColor(), entity.getColor());

        assert entity.getUser().getId() == userModel.getId();
        assert Objects.equals(entity.getUser().getUsername(), userModel.getUsername());
        assert Objects.equals(entity.getUser().getPassword(), userModel.getPassword());
        assert Objects.equals(entity.getUser().getFirstName(), userModel.getFirstName());
        assert Objects.equals(entity.getUser().getLastName(), userModel.getLastName());
        assert Objects.equals(entity.getUser().getEmail(), userModel.getEmail());
    }

}
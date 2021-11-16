package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

/**
 * Mapper used to map between User Model and Entity classes.
 *
 */
public class UserMapper implements Mapper<UserModel, UserEntity> {

    @Override
    public UserEntity mapToEntity(UserModel model) {
        if (model == null) return null;
        return new UserEntity(model.getId(), model.getUsername(), model.getPassword(), model.getFirstName(), model.getLastName(), model.getEmail());
    }

    @Override
    public UserModel mapToModel(UserEntity entity) {
        if (entity == null) return null;
        return new UserModel(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
    }
}

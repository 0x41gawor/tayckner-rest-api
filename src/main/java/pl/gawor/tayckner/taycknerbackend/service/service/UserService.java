package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.UserRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for User.
 */
@Service
public class UserService implements CRUDService<UserModel> {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    // -------------------------------------------------------------------------------------- L I S T
    @Override
    public List<UserModel> list() {
        List<UserEntity> entities = repository.findAll();
        List<UserModel> models = new ArrayList<>();
        for (UserEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }
    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public UserModel create(UserModel model) {
        model.setId(0);
        UserEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }
    // -------------------------------------------------------------------------------------- R E A D
    @Override
    public UserModel read(long id) {
        Optional<UserEntity> optional = repository.findById(id);
        UserEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }
    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public UserModel update(long id, UserModel model) {
        UserEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }
    // -------------------------------------------------------------------------------------- D E L E T E
    @Override
    public boolean delete(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

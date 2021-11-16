package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.HabitRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.HabitMapper;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Habit.
 */
@Service
public class HabitService implements CRUDService<HabitModel> {

    private final HabitRepository repository;
    private final HabitMapper mapper;

    @Autowired
    public HabitService(HabitRepository repository, HabitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    // -------------------------------------------------------------------------------------- L I S T
    @Override
    public List<HabitModel> list() {
        List<HabitEntity> entities = repository.findAll();
        List<HabitModel> models = new ArrayList<>();
        for (HabitEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }
    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public HabitModel create(HabitModel model) {
        model.setId(0);
        HabitEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }
    // -------------------------------------------------------------------------------------- R E A D
    @Override
    public HabitModel read(long id) {
        Optional<HabitEntity> optional = repository.findById(id);
        HabitEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }
    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public HabitModel update(long id, HabitModel model) {
        HabitEntity entity = mapper.mapToEntity(model);
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
    // -------------------------------------------------------------------------------------- L I S T   B Y   U S E R
    /**
     * List by user.
     * <p>
     * Returns list of model which property User is equal to given in param.
     * </p>
     * @param user user model by which search is done
     * @return List of all models in database, that has given user
     */
    public List<HabitModel> list(UserModel user) {
        UserMapper userMapper = new UserMapper();
        List<HabitEntity> entities = repository.findHabitEntitiesByUser(userMapper.mapToEntity(user));
        List<HabitModel> models = new ArrayList<>();
        for (HabitEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }
}

package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.ScheduleModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.ScheduleRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ScheduleEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.ScheduleMapper;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Schedule.
 */
@Service
public class ScheduleService implements CRUDService<ScheduleModel> {

    private final ScheduleRepository repository;
    private final ScheduleMapper mapper;

    private final UserMapper userMapper;

    @Autowired
    public ScheduleService(ScheduleRepository repository, ScheduleMapper mapper, UserMapper userMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    // -------------------------------------------------------------------------------------- L I S T
    @Override
    public List<ScheduleModel> list() {
        List<ScheduleEntity> entities = repository.findAll();
        List<ScheduleModel> models = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public ScheduleModel create(ScheduleModel model) {
        model.setId(0);
        ScheduleEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    // -------------------------------------------------------------------------------------- R E A D
    @Override
    public ScheduleModel read(long id) {
        Optional<ScheduleEntity> optional = repository.findById(id);
        ScheduleEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public ScheduleModel update(long id, ScheduleModel model) {
        ScheduleEntity entity = mapper.mapToEntity(model);
        entity.setId(id);
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
     *
     * @param user user model by which search is done
     * @return List of all models in database, that has given user
     */
    public List<ScheduleModel> list(UserModel user) {
        UserMapper userMapper = new UserMapper();
        List<ScheduleEntity> entities = repository.findScheduleEntitiesByUser(userMapper.mapToEntity(user));
        List<ScheduleModel> models = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }
    // ---------------------------------------------------------------- E X I S T S   B Y   U S E R   A N D   N A M E

    /**
     * Return true if scheudle with given name and user exists.
     */
    public boolean existByName(String name, UserModel user) {
        return repository.existsByNameAndUser(name, userMapper.mapToEntity(user));
    }
    // --------------------------------------------------------------------- E X I S T S   B Y  I D   A N D   U S E R

    /**
     * Return true if category with given id and user exists.
     */
    public boolean existsByIdAndUser(long id, UserModel user) {
        return repository.existsByIdAndUser(id, userMapper.mapToEntity(user));
    }
}

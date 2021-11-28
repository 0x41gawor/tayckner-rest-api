package pl.gawor.tayckner.taycknerbackend.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(HabitService.class);

    @Autowired
    public HabitService(HabitRepository repository, HabitMapper mapper, UserMapper userMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    // ------------------------------------------------------------------------------------------ L I S T
    @Override
    public List<HabitModel> list() {
        logger.info("HabitService :: list()");
        List<HabitEntity> entities = repository.findAll();
        List<HabitModel> models = new ArrayList<>();
        for (HabitEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("HabitService :: list() = {}", models);
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public HabitModel create(HabitModel model) {
        logger.info("HabitService :: create(model = {})", model);
        model.setId(0);
        HabitEntity entity = mapper.mapToEntity(model);
        HabitModel createdModel = mapper.mapToModel(repository.save(entity));
        logger.info("HabitService :: create(model = {}) = {}", model, createdModel);
        return createdModel;
    }

    // ------------------------------------------------------------------------------------------ R E A D
    @Override
    public HabitModel read(long id) {
        logger.info("HabitService :: read(id = {})", id);
        Optional<HabitEntity> optional = repository.findById(id);
        HabitEntity entity = optional.orElse(null);
        HabitModel readModel = mapper.mapToModel(entity);
        logger.info("HabitService :: read(id = {}) = {}", id, readModel);
        return readModel;
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public HabitModel update(long id, HabitModel model) {
        logger.info("HabitService :: update(id = {}, model = {})", id, model);
        HabitEntity entity = mapper.mapToEntity(model);
        entity.setId(id);
        HabitModel updatedModel = mapper.mapToModel(repository.save(entity));
        logger.info("HabitService :: update(id = {}, model = {}) = {}", id, model, updatedModel);
        return updatedModel;
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @Override
    public boolean delete(long id) {
        logger.info("HabitService :: delete(id = {})", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("HabitService :: delete(id = {}) = true", id);
            return true;
        }
        logger.info("HabitService :: delete(id = {}) = false", id);
        return false;
    }
    // -------------------------------------------------------------------------- L I S T   B Y   U S E R

    /**
     * List by user.
     * <p>
     * Returns list of model which property User is equal to given in param.
     * </p>
     *
     * @param user user model by which search is done
     * @return List of all models in database, that has given user
     */
    public List<HabitModel> list(UserModel user) {
        logger.info("HabitService :: list(user = {})", user);
        UserMapper userMapper = new UserMapper();
        List<HabitEntity> entities = repository.findHabitEntitiesByUser(userMapper.mapToEntity(user));
        List<HabitModel> models = new ArrayList<>();
        for (HabitEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("HabitService :: list(user = {}) = {}", user, models);
        return models;
    }
    // ---------------------------------------------------- E X I S T S   B Y   U S E R   A N D   N A M E

    /**
     * Return true if habit with given name and user exists.
     *
     * @param name name of habit to search for
     * @param user UserModel to search for
     * @return true if Habit with given user and name exists
     */
    public boolean existByName(String name, UserModel user) {
        logger.info("HabitService :: existByName(name = {}, user = {})", name, user);
        boolean result = repository.existsByNameAndUser(name, userMapper.mapToEntity(user));
        logger.info("HabitService :: existByName(name = {}, user = {}) = {}", name, user, result);
        return result;
    }
    // --------------------------------------------------------- E X I S T S   B Y  I D   A N D   U S E R

    /**
     * Return true if habit with given id and user exists.
     *
     * @param id   id of habit to search for
     * @param user UserModel to search for
     * @return true if Habit with given user and id exists
     */
    public boolean existsByIdAndUser(long id, UserModel user) {
        logger.info("HabitService :: existsByIdAndUser(id = {}, user = {})", id, user);
        boolean result = repository.existsByIdAndUser(id, userMapper.mapToEntity(user));
        logger.info("HabitService :: existsByIdAndUser(id = {}, user = {}) = {}", id, user, result);
        return result;
    }
    // ---------------------------------------------------------- F I N D   B Y   N A M E   A N D   U S E R

    /**
     * Return model of habit with given name
     *
     * @param name name of habit to search for
     * @param user user to which habit belongs
     * @return model of habit with given name
     */
    public HabitEntity findByName(String name, UserModel user) {
        logger.info("HabitService :: findByName(name = {}, user = {})", name, user);
        HabitEntity foundModel =  repository.findHabitEntityByNameAndUser(name, userMapper.mapToEntity(user));
        logger.info("HabitService :: findByName(name = {}, user = {}) = {}", name, user, foundModel);
        return foundModel;
    }
}

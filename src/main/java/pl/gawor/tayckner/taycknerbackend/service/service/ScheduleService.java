package pl.gawor.tayckner.taycknerbackend.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    public ScheduleService(ScheduleRepository repository, ScheduleMapper mapper, UserMapper userMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    // ------------------------------------------------------------------------------------------ L I S T
    @Override
    public List<ScheduleModel> list() {
        logger.info("ScheduleService :: list()");
        List<ScheduleEntity> entities = repository.findAll();
        List<ScheduleModel> models = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("ScheduleService :: list() = {}", models);
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public ScheduleModel create(ScheduleModel model) {
        logger.info("ScheduleService :: create(model = {})", model);
        model.setId(0);
        ScheduleEntity entity = mapper.mapToEntity(model);
        ScheduleModel createdModel =  mapper.mapToModel(repository.save(entity));
        logger.info("ScheduleService :: create(model = {}) = {}", model, createdModel);
        return createdModel;
    }

    // ------------------------------------------------------------------------------------------ R E A D
    @Override
    public ScheduleModel read(long id) {
        logger.info("ScheduleService :: read(id = {})", id);
        Optional<ScheduleEntity> optional = repository.findById(id);
        ScheduleEntity entity = optional.orElse(null);
        ScheduleModel readModel =  mapper.mapToModel(entity);
        logger.info("ScheduleService :: read(id = {}) = {}", id, readModel);
        return readModel;
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public ScheduleModel update(long id, ScheduleModel model) {
        logger.info("ScheduleService :: update(id = {}, model = {})", id, model);
        ScheduleEntity entity = mapper.mapToEntity(model);
        entity.setId(id);
        ScheduleModel updatedModel =  mapper.mapToModel(repository.save(entity));
        logger.info("ScheduleService :: update(id = {}, model = {}) = {}", id, model, updatedModel);
        return updatedModel;
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @Override
    public boolean delete(long id) {
        logger.info("ScheduleService :: delete(id = {})", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("ScheduleService :: delete(id = {}) = true", id);
            return true;
        }
        logger.info("ScheduleService :: delete(id = {}) = false", id);
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
    public List<ScheduleModel> list(UserModel user) {
        logger.info("ScheduleService :: list(user = {})", user);
        UserMapper userMapper = new UserMapper();
        List<ScheduleEntity> entities = repository.findScheduleEntitiesByUser(userMapper.mapToEntity(user));
        List<ScheduleModel> models = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("ScheduleService :: list(user = {}) = {}", user, models);
        return models;
    }
    // ---------------------------------------------------- E X I S T S   B Y   U S E R   A N D   N A M E

    /**
     * Return true if schedule with given name and user exists.
     *
     * @param name name of schedule to search for
     * @param user UserModel to search for
     * @return true if Schedule with given user and name exists
     */
    public boolean existByName(String name, UserModel user) {
        logger.info("ScheduleService :: existByName(name = {}, user = {})", name, user);
        boolean result = repository.existsByNameAndUser(name, userMapper.mapToEntity(user));
        logger.info("ScheduleService :: existByName(name = {}, user = {}) = {}", name, user, result);
        return result;
    }
    // --------------------------------------------------------- E X I S T S   B Y  I D   A N D   U S E R

    /**
     * Return true if schedule with given id and user exists.
     *
     * @param id   id of schedule to search for
     * @param user UserModel to search for
     * @return true if Schedule with given user and id exists
     */
    public boolean existsByIdAndUser(long id, UserModel user) {
        logger.info("ScheduleService :: existsById(id = {}, user = {})", id, user);
        boolean result = repository.existsByIdAndUser(id, userMapper.mapToEntity(user));
        logger.info("ScheduleService :: existsById(id = {}, user = {}) = {}", id, user, result);
        return  result;
    }
}

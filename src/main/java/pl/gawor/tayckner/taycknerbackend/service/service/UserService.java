package pl.gawor.tayckner.taycknerbackend.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // ------------------------------------------------------------------------------------------ L I S T
    @Override
    public List<UserModel> list() {
        logger.info("UserService :: list()");
        List<UserEntity> entities = repository.findAll();
        List<UserModel> models = new ArrayList<>();
        for (UserEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("UserService :: list() = {}", models);
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public UserModel create(UserModel model) {
        logger.info("UserService :: create(model = {})", model);
        model.setId(0);
        UserEntity entity = mapper.mapToEntity(model);
        UserModel createdModel = mapper.mapToModel(repository.save(entity));
        logger.info("UserService :: create(model = {}) = {}", model, createdModel);
        return createdModel;
    }

    // ------------------------------------------------------------------------------------------ R E A D
    @Override
    public UserModel read(long id) {
        logger.info("UserService :: read(id = {})", id);
        Optional<UserEntity> optional = repository.findById(id);
        UserEntity entity = optional.orElse(null);
        UserModel readModel =  mapper.mapToModel(entity);
        logger.info("UserService :: read(id = {}) = {}", id, readModel);
        return readModel;
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public UserModel update(long id, UserModel model) {
        logger.info("UserService :: update(id = {}, model = {})", id, model);
        UserEntity entity = mapper.mapToEntity(model);
        entity.setId(id);
        UserModel updatedModel =  mapper.mapToModel(repository.save(entity));
        logger.info("UserService :: update(id = {}, model = {}) = {}", id, model, updatedModel);
        return updatedModel;
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @Override
    public boolean delete(long id) {
        logger.info("UserService :: delete(id = {})", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("UserService :: delete(id = {}) = true", id);
            return true;
        }
        logger.info("UserService :: delete(id = {}) = false", id);
        return false;
    }

    /**
     * Method returns true if user with given username exists.
     * @param username  username for search
     * @return true if UserModel with given username is found
     */
    // ---------------------------------------------------------------E X I S T S   B Y   U S E R N A M E
    public boolean existsByUsername(String username) {
        logger.info("UserService :: existsByUsername(username = {})", username);
        boolean result =  repository.existsByUsername(username);
        logger.info("UserService :: existsByUsername(username = {}) = {}", username, result);
        return result;
    }

    /**
     * Method returns true if user with given email exists.
     * @param email  email for search
     * @return true if UserModel with given email is found
     */
    // ---------------------------------------------------------------------E X I S T S   B Y   E M A I L
    public boolean existsByEmail(String email) {
        logger.info("UserService :: existsByUsername(email = {})", email);
        boolean result =  repository.existsByEmail(email);
        logger.info("UserService :: existsByUsername(email = {}) = {}", email, result);
        return result;
    }

    /**
     * Method returns User with given username.
     * @param username username for search
     * @return UserModel with given username or null if not found
     */
    // -------------------------------------------------------------------F I N D   B Y   U S E R N A M E
    public UserModel findByUsername(String username) {
        logger.info("UserService :: findByUsername(username = {})", username);
        UserModel foundModel =  mapper.mapToModel(repository.findUserEntityByUsername(username));
        logger.info("UserService :: findByUsername(username = {}) = {}", username, foundModel);
        return foundModel;
    }
}

package pl.gawor.tayckner.taycknerbackend.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.CategoryRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.CategoryMapper;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Category.
 */
@Service
public class CategoryService implements CRUDService<CategoryModel> {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper, UserMapper userMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    // ------------------------------------------------------------------------------------------ L I S T
    @Override
    public List<CategoryModel> list() {
        logger.info("CategoryService :: list()");
        List<CategoryEntity> entities = repository.findAll();
        List<CategoryModel> models = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("CategoryService :: list() = {}", models);
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public CategoryModel create(CategoryModel model) {
        logger.info("CategoryService :: create(model = {})", model);
        model.setId(0);
        CategoryEntity entity = mapper.mapToEntity(model);
        CategoryModel createdModel = mapper.mapToModel(repository.save(entity));
        logger.info("CategoryService :: create(model = {}) = {}", model, createdModel);
        return createdModel;
    }

    // ------------------------------------------------------------------------------------------ R E A D
    @Override
    public CategoryModel read(long id) {
        logger.info("CategoryService :: read(id = {})", id);
        Optional<CategoryEntity> optional = repository.findById(id);
        CategoryEntity entity = optional.orElse(null);
        CategoryModel readModel = mapper.mapToModel(entity);
        logger.info("CategoryService :: read(id = {}) = {}", id, readModel);
        return readModel;
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public CategoryModel update(long id, CategoryModel model) {
        logger.info("CategoryService :: update(id = {}, model = {})", id, model);
        CategoryEntity entity = mapper.mapToEntity(model);
        entity.setId(id);
        CategoryModel updatedModel = mapper.mapToModel(repository.save(entity));
        logger.info("CategoryService :: update(id = {}, model = {}) = {}", id, model, updatedModel);
        return updatedModel;
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @Override
    public boolean delete(long id) {
        logger.info("CategoryService :: delete(id = {})", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("CategoryService :: delete(id = {}) = true", id);
            return true;
        }
        logger.info("CategoryService :: delete(id = {}) = false", id);
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
    public List<CategoryModel> list(UserModel user) {
        logger.info("CategoryService :: list(user = {})", user);
        UserMapper userMapper = new UserMapper();
        List<CategoryEntity> entities = repository.findCategoryEntitiesByUser(userMapper.mapToEntity(user));
        List<CategoryModel> models = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("CategoryService :: list(user = {}) = {}", user, models);
        return models;
    }
    // ---------------------------------------------------- E X I S T S   B Y   U S E R   A N D   N A M E

    /**
     * Return true if category with given name and user exists.
     *
     * @param name name of category to search for
     * @param user UserModel to search for
     * @return true if Category with given user and name exists
     */
    public boolean existByName(String name, UserModel user) {
        logger.info("CategoryService :: existByName(name = {}, user = {})", name, user);
        boolean result = repository.existsByNameAndUser(name, userMapper.mapToEntity(user));
        logger.info("CategoryService :: existByName(name = {}, user = {}) = {}", name, user, result);
        return result;
    }
    // --------------------------------------------------------- E X I S T S   B Y  I D   A N D   U S E R

    /**
     * Return true if category with given id and user exists.
     *
     * @param id   id of category to search for
     * @param user UserModel to search for
     * @return true if Category with given user and id exists
     */
    public boolean existsByIdAndUser(long id, UserModel user) {
        logger.info("CategoryService :: existsByIdAndUser(id = {}, user = {})", id, user);
        boolean result = repository.existsByIdAndUser(id, userMapper.mapToEntity(user));
        logger.info("CategoryService :: existsByIdAndUser(id = {}, user = {}) = {}", id, user, result);
        return result;
    }
    // ---------------------------------------------------------- F I N D   B Y   N A M E   A N D   U S E R

    /**
     * Return model of category with given name
     *
     * @param name name of category to search for
     * @param user user to which category belongs
     * @return model of category with given name
     */
    public CategoryEntity findByName(String name, UserModel user) {
        logger.info("CategoryService :: findByName(name = {}, user = {})", name, user);
        CategoryEntity foundEntity = repository.findCategoryEntityByNameAndUser(name, userMapper.mapToEntity(user));
        logger.info("CategoryService :: findByName(name = {}, user = {}) = {}", name, user, foundEntity);
        return foundEntity;
    }
}

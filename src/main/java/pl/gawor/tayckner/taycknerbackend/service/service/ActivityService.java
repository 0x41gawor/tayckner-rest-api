package pl.gawor.tayckner.taycknerbackend.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.repository.ActivityRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ActivityEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.ActivityMapper;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.CategoryMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Activity.
 */
@Service
public class ActivityService implements CRUDService<ActivityModel> {

    private final ActivityRepository repository;
    private final ActivityMapper mapper;

    private final CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(ActivityService.class);

    @Autowired
    public ActivityService(ActivityRepository repository, ActivityMapper mapper, CategoryService categoryService, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    // ------------------------------------------------------------------------------------------ L I S T
    @Override
    public List<ActivityModel> list() {
        logger.info("ActivityService :: list()");
        List<ActivityEntity> entities = repository.findAll();
        List<ActivityModel> models = new ArrayList<>();
        for (ActivityEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("ActivityService :: list() = {}", models);
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public ActivityModel create(ActivityModel model) {
        logger.info("ActivityService :: create(model = {})", model);
        model.setId(0);
        ActivityEntity entity = mapper.mapToEntity(model);
        ActivityModel createdModel = mapper.mapToModel(repository.save(entity));
        logger.info("ActivityService :: create(model = {}) = {}", model, createdModel);
        return createdModel;
    }

    // ------------------------------------------------------------------------------------------ R E A D
    @Override
    public ActivityModel read(long id) {
        logger.info("ActivityService :: read(id = {})", id);
        Optional<ActivityEntity> optional = repository.findById(id);
        ActivityEntity entity = optional.orElse(null);
        ActivityModel readModel = mapper.mapToModel(entity);
        logger.info("ActivityService :: read(id = {}) = {}", id, readModel);
        return readModel;
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public ActivityModel update(long id, ActivityModel model) {
        logger.info("ActivityService :: update(id = {}, model = {})", id, model);
        ActivityEntity entity = mapper.mapToEntity(model);
        entity.setId(id);
        ActivityModel updatedModel = mapper.mapToModel(repository.save(entity));
        logger.info("ActivityService :: update(id = {}, model = {}) = {}", id, model, updatedModel);
        return updatedModel;
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @Override
    public boolean delete(long id) {
        logger.info("ActivityService :: delete(id = {})", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            logger.info("ActivityService :: delete(id = {}) = true", id);
            return true;
        }
        logger.info("ActivityService :: delete(id = {}) = false", id);
        return false;
    }
    // ------------------------------------------------------------------ L I S T   B Y   C A T E G O R Y

    /**
     * List by category.
     * <p>
     * Returns list of model which property Category is equal to given in param.
     * </p>
     *
     * @param category category model by which search is done
     * @return List of all models in database, that has given user
     */
    public List<ActivityModel> list(CategoryModel category) {
        logger.info("ActivityService :: list(category = {})", category);
        CategoryMapper categoryMapper = new CategoryMapper();
        List<ActivityEntity> entities = repository.findActivityEntitiesByCategory(categoryMapper.mapToEntity(category));
        List<ActivityModel> models = new ArrayList<>();
        for (ActivityEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        logger.info("ActivityService :: list(category = {}) = {}", category, models);
        return models;
    }

    // -------------------------------------------------------------------------- L I S T   B Y   U S E R

    /**
     * List by user.
     * <p>
     * Returns list of model which Category's property User is equal to given in param.
     * </p>
     *
     * @param user user model by which search is done
     * @return List of all models in database, that has given user
     */
    public List<ActivityModel> list(UserModel user) {
        logger.info("ActivityService :: list(user = {})", user);
        List<CategoryModel> categories = categoryService.list(user);
        List<ActivityModel> activities = new ArrayList<>();
        for (CategoryModel category : categories) {
            for (ActivityModel activity : list(category)) {
                activity.getCategory().getUser().setPassword("");
                activities.add(activity);
            }
        }
        logger.info("ActivityService :: list(user = {}) = {}", user, activities);
        return activities;
    }

    /**
     * Return true if activity event with given id exists.
     *
     * @param id id to search for
     * @return true if Activity with given id exists
     */
    // -------------------------------------------------------------------------- E X I S T S   B Y   I D
    public boolean existsById(long id) {
        logger.info("ActivityService :: existsById(id = {})", id);
        boolean result = repository.existsById(id);
        logger.info("ActivityService :: existsById(id = {}) = {}", id, result);
        return result;
    }

}

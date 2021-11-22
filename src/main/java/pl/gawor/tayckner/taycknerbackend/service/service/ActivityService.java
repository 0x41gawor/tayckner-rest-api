package pl.gawor.tayckner.taycknerbackend.service.service;

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

    @Autowired
    public ActivityService(ActivityRepository repository, ActivityMapper mapper, CategoryService categoryService, UserService userService) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryService = categoryService;
    }

    // -------------------------------------------------------------------------------------- L I S T
    @Override
    public List<ActivityModel> list() {
        List<ActivityEntity> entities = repository.findAll();
        List<ActivityModel> models = new ArrayList<>();
        for (ActivityEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public ActivityModel create(ActivityModel model) {
        model.setId(0);
        ActivityEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    // -------------------------------------------------------------------------------------- R E A D
    @Override
    public ActivityModel read(long id) {
        Optional<ActivityEntity> optional = repository.findById(id);
        ActivityEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public ActivityModel update(long id, ActivityModel model) {
        ActivityEntity entity = mapper.mapToEntity(model);
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
    // -------------------------------------------------------------------------------------- L I S T   B Y   C A T E G O R Y

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
        CategoryMapper categoryMapper = new CategoryMapper();
        List<ActivityEntity> entities = repository.findActivityEntitiesByCategory(categoryMapper.mapToEntity(category));
        List<ActivityModel> models = new ArrayList<>();
        for (ActivityEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    public List<ActivityModel> list(UserModel user) {
        List<CategoryModel> categories = categoryService.list(user);
        List<ActivityModel> activities = new ArrayList<>();
        for (CategoryModel category : categories) {
            for (ActivityModel activity : list(category)) {
                activity.getCategory().getUser().setPassword("");
                activities.add(activity);
            }
        }
        return activities;
    }
}

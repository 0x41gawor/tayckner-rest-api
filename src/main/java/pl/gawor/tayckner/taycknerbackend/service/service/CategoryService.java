package pl.gawor.tayckner.taycknerbackend.service.service;

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

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // -------------------------------------------------------------------------------------- L I S T
    @Override
    public List<CategoryModel> list() {
        List<CategoryEntity> entities = repository.findAll();
        List<CategoryModel> models = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public CategoryModel create(CategoryModel model) {
        model.setId(0);
        CategoryEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    // -------------------------------------------------------------------------------------- R E A D
    @Override
    public CategoryModel read(long id) {
        Optional<CategoryEntity> optional = repository.findById(id);
        CategoryEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public CategoryModel update(long id, CategoryModel model) {
        CategoryEntity entity = mapper.mapToEntity(model);
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
    public List<CategoryModel> list(UserModel user) {
        UserMapper userMapper = new UserMapper();
        List<CategoryEntity> entities = repository.findCategoryEntitiesByUser(userMapper.mapToEntity(user));
        List<CategoryModel> models = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }
}

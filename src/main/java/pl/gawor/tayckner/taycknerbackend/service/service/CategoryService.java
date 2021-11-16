package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.repository.CategoryRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.CategoryMapper;

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

    @Override
    public List<CategoryModel> list() {
        List<CategoryEntity> entities = repository.findAll();
        List<CategoryModel> models = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    @Override
    public CategoryModel create(CategoryModel model) {
        model.setId(0);
        CategoryEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    @Override
    public CategoryModel read(long id) {
        Optional<CategoryEntity> optional = repository.findById(id);
        CategoryEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    @Override
    public CategoryModel update(long id, CategoryModel model) {
        CategoryEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    @Override
    public boolean delete(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.CategoryEntity;

/**
 * Mapper used to map between Category Model and Entity classes.
 *
 */
@Component
public class CategoryMapper implements Mapper<CategoryModel, CategoryEntity> {

    @Override
    public CategoryEntity mapToEntity(CategoryModel model) {
        if(model == null) return null;
        UserMapper userMapper = new UserMapper();
        return new CategoryEntity(model.getId(), model.getName(), model.getDescription(), model.getColor(), userMapper.mapToEntity(model.getUser()));
    }

    @Override
    public CategoryModel mapToModel(CategoryEntity entity) {
        if(entity == null) return null;
        UserMapper userMapper = new UserMapper();
        return new CategoryModel(entity.getId(), entity.getName(), entity.getDescription(), entity.getColor(), userMapper.mapToModel(entity.getUser()));
    }
}

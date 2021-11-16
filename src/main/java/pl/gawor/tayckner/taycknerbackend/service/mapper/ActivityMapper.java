package pl.gawor.tayckner.taycknerbackend.service.mapper;

import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ActivityEntity;

/**
 * Mapper used to map between Activity Model and Entity classes.
 *
 */
public class ActivityMapper implements Mapper<ActivityModel, ActivityEntity> {
    @Override
    public ActivityEntity mapToEntity(ActivityModel model) {
        if (model == null) return null;
        CategoryMapper categoryMapper = new CategoryMapper();
        return new ActivityEntity(model.getId(), model.getName(), categoryMapper.mapToEntity(model.getCategory()),
                model.getStartTime(), model.getEndTime(), model.getDuration(), model.getBreaks());
    }

    @Override
    public ActivityModel mapToModel(ActivityEntity entity) {
        if (entity == null) return null;
        CategoryMapper categoryMapper = new CategoryMapper();
        return new ActivityModel(entity.getId(), entity.getName(), categoryMapper.mapToModel(entity.getCategory()),
                entity.getStartTime(), entity.getEndTime(), entity.getDuration(), entity.getBreaks());
    }
}

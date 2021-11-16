package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

import pl.gawor.tayckner.taycknerbackend.core.model.ScheduleModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ScheduleEntity;

/**
 * Mapper used to map between Schedule Model and Entity classes.
 *
 */
public class ScheduleMapper implements Mapper<ScheduleModel, ScheduleEntity> {
    @Override
    public ScheduleEntity mapToEntity(ScheduleModel model) {
        if (model == null) return null;
        UserMapper userMapper = new UserMapper();
        return new ScheduleEntity(model.getId(), model.getName(), model.getStartTime(),
                model.getEndTime(), model.getDuration(), userMapper.mapToEntity(model.getUser()));
    }

    @Override
    public ScheduleModel mapToModel(ScheduleEntity entity) {
        if (entity == null) return null;
        UserMapper userMapper = new UserMapper();
        return new ScheduleModel(entity.getId(), entity.getName(), entity.getStartTime(),
                entity.getEndTime(), entity.getDuration(), userMapper.mapToModel(entity.getUser()));
    }
}

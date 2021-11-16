package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;

/**
 * Mapper used to map between Habit Model and Entity classes.
 *
 */
@Component
public class HabitMapper implements Mapper<HabitModel, HabitEntity> {
    @Override
    public HabitEntity mapToEntity(HabitModel model) {
        if (model == null) return null;
        UserMapper userMapper = new UserMapper();
        return new HabitEntity(model.getId(), model.getName(), model.getColor(), userMapper.mapToEntity(model.getUser()));
    }

    @Override
    public HabitModel mapToModel(HabitEntity entity) {
        if (entity == null) return null;
        UserMapper userMapper = new UserMapper();
        return new HabitModel(entity.getId(), entity.getName(), entity.getColor(), userMapper.mapToModel(entity.getUser()));
    }
}

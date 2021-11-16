package pl.gawor.tayckner.taycknerbackend.service.mapper;

import pl.gawor.tayckner.taycknerbackend.core.model.HabitEventModel;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEventEntity;

/**
 * Mapper used to map between HabitEvent Model and Entity classes.
 *
 */
public class HabitEventMapper implements Mapper<HabitEventModel, HabitEventEntity> {
    @Override
    public HabitEventEntity mapToEntity(HabitEventModel model) {
        if (model == null) return null;
        HabitMapper habitMapper = new HabitMapper();
        return new HabitEventEntity(model.getId(), habitMapper.mapToEntity(model.getHabit()), model.getDate(), model.getComment(), model.getValue());
    }

    @Override
    public HabitEventModel mapToModel(HabitEventEntity entity) {
        if (entity == null) return null;
        HabitMapper habitMapper = new HabitMapper();
        return new HabitEventModel(entity.getId(), habitMapper.mapToModel(entity.getHabit()), entity.getDate(), entity.getComment(), entity.getValue());
    }
}

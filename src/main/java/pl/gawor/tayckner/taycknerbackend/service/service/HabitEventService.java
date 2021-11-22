package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitEventModel;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.repository.HabitEventRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEventEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.HabitEventMapper;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.HabitMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for HabitEvent.
 */
@Service
public class HabitEventService implements CRUDService<HabitEventModel> {

    private final HabitEventRepository repository;
    private final HabitEventMapper mapper;

    @Autowired
    public HabitEventService(HabitEventRepository repository, HabitEventMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // -------------------------------------------------------------------------------------- L I S T
    @Override
    public List<HabitEventModel> list() {
        List<HabitEventEntity> entities = repository.findAll();
        List<HabitEventModel> models = new ArrayList<>();
        for (HabitEventEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @Override
    public HabitEventModel create(HabitEventModel model) {
        model.setId(0);
        HabitEventEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    // -------------------------------------------------------------------------------------- R E A D
    @Override
    public HabitEventModel read(long id) {
        Optional<HabitEventEntity> optional = repository.findById(id);
        HabitEventEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @Override
    public HabitEventModel update(long id, HabitEventModel model) {
        HabitEventEntity entity = mapper.mapToEntity(model);
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
    // -------------------------------------------------------------------------------------- L I S T   B Y   H A B I T

    /**
     * List by habit.
     * <p>
     * Returns list of model which property Habit is equal to given in param.
     * </p>
     *
     * @param habit habit model by which search is done
     * @return List of all models in database, that has given user
     */
    public List<HabitEventModel> list(HabitModel habit) {
        HabitMapper habitMapper = new HabitMapper();
        List<HabitEventEntity> entities = repository.findHabitEventEntitiesByHabit(habitMapper.mapToEntity(habit));
        List<HabitEventModel> models = new ArrayList<>();
        for (HabitEventEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }
}

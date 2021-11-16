package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitEventModel;
import pl.gawor.tayckner.taycknerbackend.repository.HabitEventRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEventEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.HabitEventMapper;

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

    @Override
    public List<HabitEventModel> list() {
        List<HabitEventEntity> entities = repository.findAll();
        List<HabitEventModel> models = new ArrayList<>();
        for (HabitEventEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    @Override
    public HabitEventModel create(HabitEventModel model) {
        model.setId(0);
        HabitEventEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    @Override
    public HabitEventModel read(long id) {
        Optional<HabitEventEntity> optional = repository.findById(id);
        HabitEventEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    @Override
    public HabitEventModel update(long id, HabitEventModel model) {
        HabitEventEntity entity = mapper.mapToEntity(model);
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
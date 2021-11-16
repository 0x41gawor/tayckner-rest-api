package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.repository.HabitRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.HabitEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.HabitMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Habit.
 */
@Service
public class HabitService implements CRUDService<HabitModel> {

    private final HabitRepository repository;
    private final HabitMapper mapper;

    @Autowired
    public HabitService(HabitRepository repository, HabitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<HabitModel> list() {
        List<HabitEntity> entities = repository.findAll();
        List<HabitModel> models = new ArrayList<>();
        for (HabitEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    @Override
    public HabitModel create(HabitModel model) {
        model.setId(0);
        HabitEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    @Override
    public HabitModel read(long id) {
        Optional<HabitEntity> optional = repository.findById(id);
        HabitEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    @Override
    public HabitModel update(long id, HabitModel model) {
        HabitEntity entity = mapper.mapToEntity(model);
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

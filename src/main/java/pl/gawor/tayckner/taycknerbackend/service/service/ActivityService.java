package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.repository.ActivityRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ActivityEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.ActivityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Activity.
 */
@Service
public class ActivityService implements CRUDService<ActivityModel> {
    private final ActivityRepository repository;
    private final ActivityMapper mapper;

    @Autowired
    public ActivityService(ActivityRepository repository, ActivityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ActivityModel> list() {
        List<ActivityEntity> entities = repository.findAll();
        List<ActivityModel> models = new ArrayList<>();
        for (ActivityEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    @Override
    public ActivityModel create(ActivityModel model) {
        model.setId(0);
        ActivityEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    @Override
    public ActivityModel read(long id) {
        Optional<ActivityEntity> optional = repository.findById(id);
        ActivityEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    @Override
    public ActivityModel update(long id, ActivityModel model) {
        ActivityEntity entity = mapper.mapToEntity(model);
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

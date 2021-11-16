package pl.gawor.tayckner.taycknerbackend.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.gawor.tayckner.taycknerbackend.core.model.ScheduleModel;
import pl.gawor.tayckner.taycknerbackend.repository.ScheduleRepository;
import pl.gawor.tayckner.taycknerbackend.repository.entity.ScheduleEntity;
import pl.gawor.tayckner.taycknerbackend.service.service.mapper.ScheduleMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for Schedule.
 */
@Service
public class ScheduleService implements CRUDService<ScheduleModel> {

    private final ScheduleRepository repository;
    private final ScheduleMapper mapper;

    @Autowired
    public ScheduleService(ScheduleRepository repository, ScheduleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ScheduleModel> list() {
        List<ScheduleEntity> entities = repository.findAll();
        List<ScheduleModel> models = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return models;
    }

    @Override
    public ScheduleModel create(ScheduleModel model) {
        model.setId(0);
        ScheduleEntity entity = mapper.mapToEntity(model);
        return mapper.mapToModel(repository.save(entity));
    }

    @Override
    public ScheduleModel read(long id) {
        Optional<ScheduleEntity> optional = repository.findById(id);
        ScheduleEntity entity = optional.orElse(null);
        return mapper.mapToModel(entity);
    }

    @Override
    public ScheduleModel update(long id, ScheduleModel model) {
        ScheduleEntity entity = mapper.mapToEntity(model);
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

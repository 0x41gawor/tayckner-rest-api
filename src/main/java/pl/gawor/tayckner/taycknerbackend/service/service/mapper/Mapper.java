package pl.gawor.tayckner.taycknerbackend.service.service.mapper;

/**
 * Interface that defines mappers behavior.
 *
 */
public interface Mapper<M, E> {
    /**
     * Maps class from Model to Entity
     *
     * @param model model class
     * @return entity class
     */
    E mapToEntity(M model);
    /**
     * Maps class from Entity to Model
     *
     * @param entity entity class
     * @return model class
     */
    M mapToModel(E entity);
}

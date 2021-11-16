package pl.gawor.tayckner.taycknerbackend.service.service;

import java.util.List;

/**
 * Interface for defining behavior of CRUD services for model classes.
 * <p>
 * Model classes services will implement it and extend with their own specific methods.
 *</p>
 */
public interface CRUDService<Model> {
    List<Model> list();
    Model create(Model model);
    Model read(long id);
    Model update(long id, Model model);
    boolean delete(long id);
}
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
    Model read(int id);
    Model update(int id, Model model);
    boolean delete(int id);
}
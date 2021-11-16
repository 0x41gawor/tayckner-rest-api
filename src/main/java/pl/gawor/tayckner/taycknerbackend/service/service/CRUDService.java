package pl.gawor.tayckner.taycknerbackend.service.service;

import java.util.List;

/**
 * Interface for defining behavior of CRUD services for model classes.
 * <p>
 * Model classes services will implement it and extend with their own specific methods.
 *</p>
 */
public interface CRUDService<Model> {
    public List<Model> list();
    public Model create(Model model);
    public Model read(int id);
    public Model update(int id, Model model);
    public boolean delete(int id);
}
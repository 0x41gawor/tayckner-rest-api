package pl.gawor.tayckner.taycknerbackend.service.service;

import java.util.List;

/**
 * Interface for defining behavior of CRUD services for model classes.
 * <p>
 * Model classes services will implement it and extend with their own specific methods.
 *</p>
 */
public interface CRUDService<Model> {
    /**
     * List CRUD method.
     *
     * Note that this method can return an empty list.
     *
     * @return List of all models in database
     */
    List<Model> list();
    /**
     * Create CRUD method.
     * <P>
     *     This method sets model id to 0, because when Hibernate sees objects with such id, he knows he has to add new row in relation.
     * </P>
     * @param model model that has to be created
     * @return created model
     */
    Model create(Model model);
    /**
     * Read CRUD method.
     *
     * <P>
     *      If no object exists with given id method returns null
     * </P>
     * @param id id of model to be returned
     * @return model with given id (or null)
     */
    Model read(long id);
    /**
     * Update CRUD method.
     *
     * <P>
     *     Method replaces object with given id with new model
     * </P>
     * @param id id of model to be updated
     * @param model new model
     * @return new updated model saved to the database
     */
    Model update(long id, Model model);
    /**
     * Delete CRUD method.
     *
     * <P>
     *     Method checks if object with given id exits if not it returns false, <br>
     *     if object exists and deletion was successful it returns true.
     * </P>
     * @param id id of model to be deleted
     * @return true of false
     */
    boolean delete(long id);
}
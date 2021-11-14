package pl.gawor.tayckner.taycknerbackend.core.model;

/**
 * Model class for `Category`.
 *
 * A simple POJO.
 */
public class CategoryModel {

// -------------------------------------------------------------------------------------- F I E L D S

    private long id;
    private String name;
    private String description;
    private String color;
    private UserModel user;

// -------------------------------------------------------------------------------------- C O N S T R U C T O R S

    public CategoryModel(long id, String name, String description, String color, UserModel user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.user = user;
    }

// -------------------------------------------------------------------------------------- G E T T E R S / S E T T E R S

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

// -------------------------------------------------------------------------------------- T O  S T R I N G

    @Override
    public String toString() {
        return "CategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", user=" + user +
                '}';
    }

}

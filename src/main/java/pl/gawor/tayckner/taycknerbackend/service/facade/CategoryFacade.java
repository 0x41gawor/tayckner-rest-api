package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.service.CategoryService;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;

/**
 * Facade class for `Category` endpoints.
 */
@Component
public class CategoryFacade {

    private final CategoryService service;
    private final UserService userService;

    public CategoryFacade(CategoryService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    public Response create(CategoryModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;

        UserModel user = userService.read(userId);
        try {
            // validate name
            if (service.existByName(model.getName(), user)) {
                responseStatus = ResponseStatus.MCC1;
                throw new ValidationException();
            }
            // validate color
            if (!Color.validate(model.getColor()) || model.getColor().length() > 7) {
                responseStatus = ResponseStatus.MAC2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response.Builder builder = new Response.Builder();
            return builder
                    .setResponseStatus(responseStatus)
                    .build();
        }

        model.setId(0);
        model.setUser(user);
        CategoryModel createdModel = service.create(model);
        createdModel.getUser().setPassword("");

        Response.Builder builder = new Response.Builder();
        return builder
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
    }
}

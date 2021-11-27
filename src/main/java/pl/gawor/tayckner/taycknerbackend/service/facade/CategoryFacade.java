package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.Color;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.ValidationException;
import pl.gawor.tayckner.taycknerbackend.service.service.CategoryService;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;

import java.util.List;

/**
 * Facade class for `Category` endpoints.
 */
@Component
public class CategoryFacade {

    private final CategoryService service;
    private final UserService userService;

    private final Response.Builder builder;

    private final Logger logger = LoggerFactory.getLogger(CategoryFacade.class);

    public CategoryFacade(CategoryService service, UserService userService) {
        this.service = service;
        this.userService = userService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        logger.info("CategoryFacade :: list(userId = {})", userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        List<CategoryModel> models = service.list(user);

        if (models.isEmpty()) {
            responseStatus = ResponseStatus.XxL1;
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("CategoryFacade :: list(userId = {}) = {}", userId, response);
            return response;
        }

        models.forEach((c) -> c.getUser().setPassword(""));

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(models)
                .build();
        logger.info("CategoryFacade :: list(userId = {}) = {}", userId,response);
        return response;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    public Response create(CategoryModel model, long userId) {
        logger.info("CategoryFacade :: create(model = {}, userId = {})", model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);
        try {
            // validate name
            if (service.existByName(model.getName(), user)) {
                responseStatus = ResponseStatus.CaX1;
                throw new ValidationException();
            }
            // validate color
            if (!Color.validate(model.getColor()) || model.getColor().length() > 7) {
                responseStatus = ResponseStatus.XxX3;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("CategoryFacade :: create(model = {}, userId) = {}) = {}}", model, userId, response);
            return response;
        }

        model.setId(0);
        model.setUser(user);
        CategoryModel createdModel = service.create(model);
        createdModel.getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
        logger.info("CategoryFacade :: create(model = {}, userId) = {}) = {}}", model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- R E A D
    public Response read(long id, long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);

        try {
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("CategoryFacade :: read(id = {}, userId = {}) = {}}", id, userId, response);
            return response;
        }

        CategoryModel readModel = service.read(id);
        readModel.getUser().setPassword("");
        Response response = builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
        logger.info("CategoryFacade :: read(id = {}, userId = {}) = {}}", id, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- U P D A T E
    public Response update(long id, CategoryModel model, long userId) {
        logger.info("CategoryFacade :: update(id = {}, model = {}, userId = {})", id, model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);

        try {
            // validate user and id
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate name
            if (service.existByName(model.getName(), user)) {
                if(service.findByName(model.getName(), user).getId() != id){
                    responseStatus = ResponseStatus.CaX1;
                    throw new ValidationException();
                }
            }
            // validate color
            if (!Color.validate(model.getColor())) {
                responseStatus = ResponseStatus.XxX3;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("CategoryFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
            return response;
        }
        model.setUser(user);
        CategoryModel updatedModel = service.update(id, model);
        updatedModel.getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(updatedModel)
                .build();
        logger.info("CategoryFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- D E L E T E
    public Response delete(long id, long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        try {
            // validate user and id
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("CategoryFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
            return response;
        }

        if (!service.delete(id)) {
            responseStatus = ResponseStatus.XxX2;
        }

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
        logger.info("CategoryFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
        return response;
    }
}

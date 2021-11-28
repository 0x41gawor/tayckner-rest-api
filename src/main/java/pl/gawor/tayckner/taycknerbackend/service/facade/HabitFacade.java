package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.Color;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.ValidationException;
import pl.gawor.tayckner.taycknerbackend.service.service.HabitService;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;

import java.util.List;

/**
 * Facade class for `Habit` endpoints.
 */
@Component
public class HabitFacade {

    private final HabitService service;
    private final UserService userService;

    private final Response.Builder builder;

    private final Logger logger = LoggerFactory.getLogger(HabitFacade.class);

    public HabitFacade(HabitService service, UserService userService) {
        this.service = service;
        this.userService = userService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        logger.info("HabitFacade :: list(userId = {})", userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        List<HabitModel> models = service.list(user);

        if (models.isEmpty()) {
            responseStatus = ResponseStatus.XxL1;
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("HabitFacade :: list(userId = {}) = {}", userId, response);
            return response;
        }

        models.forEach((h) -> h.getUser().setPassword(""));

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(models)
                .build();
        logger.info("HabitFacade :: list(userId = {}) = {}", userId, response);
        return response;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    public Response create(HabitModel model, long userId) {
        logger.info("HabitFacade :: create(model = {}, userId = {})", model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);
        try {
            // validate name
            if (service.existByName(model.getName(), user)) {
                responseStatus = ResponseStatus.HaX1;
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
            logger.info("HabitFacade :: create(model = {}, userId = {}) = {}}", model, userId, response);
            return response;
        }

        model.setId(0);
        model.setUser(user);
        HabitModel createdModel = service.create(model);
        createdModel.getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
        logger.info("HabitFacade :: create(model = {}, userId = {}) = {}}", model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- R E A D
    public Response read(long id, long userId) {
        logger.info("HabitFacade :: read(id = {}, userId = {})", id, userId);
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
            logger.info("HabitFacade :: read(id = {}, userId = {}) = {}", id, userId, response);
            return response;
        }

        HabitModel readModel = service.read(id);
        readModel.getUser().setPassword("");

        Response response = builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
        logger.info("HabitFacade :: read(id = {}, userId = {}) = {}}", id, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- U P D A T E
    public Response update(long id, HabitModel model, long userId) {
        logger.info("HabitFacade :: update(id = {}, model = {}, userId = {})", id, model, userId);
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
                if (service.findByName(model.getName(), user).getId() != id) {
                    responseStatus = ResponseStatus.HaX1;
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
            logger.info("HabitFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
            return response;
        }
        model.setUser(user);
        HabitModel updatedModel = service.update(id, model);
        updatedModel.getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(updatedModel)
                .build();
        logger.info("HabitFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- D E L E T E
    public Response delete(long id, long userId) {
        logger.info("HabitFacade :: delete(id = {}, userId = {})", id, userId);
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
            logger.info("HabitFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
            return response;
        }

        if (!service.delete(id)) {
            responseStatus = ResponseStatus.XxX2;
        }

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
        logger.info("HabitFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
        return response;
    }
}

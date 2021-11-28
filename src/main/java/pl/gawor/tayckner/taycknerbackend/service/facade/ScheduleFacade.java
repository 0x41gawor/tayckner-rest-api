package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.ScheduleModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.ValidationException;
import pl.gawor.tayckner.taycknerbackend.service.service.ScheduleService;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;

import java.util.List;

/**
 * Facade class for `Category` endpoints.
 */
@Component
public class ScheduleFacade {

    private final ScheduleService service;
    private final UserService userService;

    private final Response.Builder builder;

    private final Logger logger = LoggerFactory.getLogger(ScheduleFacade.class);


    public ScheduleFacade(ScheduleService service, UserService userService) {
        this.service = service;
        this.userService = userService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        logger.info("ScheduleFacade :: list(userId = {})", userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        List<ScheduleModel> models = service.list(user);

        if (models.isEmpty()) {
            responseStatus = ResponseStatus.XxL1;
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("ScheduleFacade :: list(userId = {}) = {}", userId, response);
            return response;
        }

        models.forEach((s) -> s.getUser().setPassword(""));

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(models)
                .build();
        logger.info("ScheduleFacade :: list(userId = {}) = {}", userId, response);
        return response;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    public Response create(ScheduleModel model, long userId) {
        logger.info("ScheduleFacade :: create(model = {}, userId = {})", model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);
        try {
            // validate times
            if (model.getStartTime().isAfter(model.getEndTime())) {
                responseStatus = ResponseStatus.XxX4;
                throw new ValidationException();
            }
            // validate duration
            if (model.getDuration() < 0) {
                responseStatus = ResponseStatus.XxX5;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("ScheduleFacade :: create(model = {}, userId) = {}) = {}}", model, userId, response);
            return response;
        }

        model.setId(0);
        model.setUser(user);
        ScheduleModel createdModel = service.create(model);
        createdModel.getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
        logger.info("ScheduleFacade :: create(model = {}, userId) = {})", model, userId);
        return response;
    }

    // ------------------------------------------------------------------------------------------- R E A D
    public Response read(long id, long userId) {
        logger.info("ScheduleFacade :: read(id = {}, userId = {})", id, userId);
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
            logger.info("ScheduleFacade :: read(id = {}, userId = {}) = {}", id, userId, response);
            return response;
        }

        ScheduleModel readModel = service.read(id);
        readModel.getUser().setPassword("");

        Response response = builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
        logger.info("ScheduleFacade :: read(id = {}, userId = {}) = {}", id, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- U P D A T E
    public Response update(long id, ScheduleModel model, long userId) {
        logger.info("ScheduleFacade :: update(id = {}, model = {}, userId = {})", id, model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);

        try {
            // validate user and id
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate times
            if (model.getStartTime().isAfter(model.getEndTime())) {
                responseStatus = ResponseStatus.XxX4;
                throw new ValidationException();
            }
            // validate duration
            if (model.getDuration() < 0) {
                responseStatus = ResponseStatus.XxX5;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("ScheduleFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
            return response;
        }
        model.setUser(user);
        ScheduleModel updatedModel = service.update(id, model);

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(updatedModel)
                .build();
        logger.info("ScheduleFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- D E L E T E
    public Response delete(long id, long userId) {
        logger.info("ScheduleFacade :: delete(id = {}, userId = {})", id, userId);
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
            logger.info("ScheduleFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
            return response;
        }

        if (!service.delete(id)) {
            responseStatus = ResponseStatus.XxX2;
        }

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
        logger.info("ScheduleFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
        return response;
    }
}

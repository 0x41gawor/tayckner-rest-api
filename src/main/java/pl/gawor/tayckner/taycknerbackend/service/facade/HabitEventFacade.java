package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitEventModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.ValidationException;
import pl.gawor.tayckner.taycknerbackend.service.service.HabitEventService;
import pl.gawor.tayckner.taycknerbackend.service.service.HabitService;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;

import java.util.List;

/**
 * Facade class for `Habit event` endpoints.
 */
@Component
public class HabitEventFacade {

    private final HabitEventService service;
    private final UserService userService;
    private final HabitService habitService;

    private final Response.Builder builder;

    private final Logger logger = LoggerFactory.getLogger(HabitEventFacade.class);

    public HabitEventFacade(HabitEventService service, UserService userService, HabitService habitService) {
        this.service = service;
        this.userService = userService;
        this.habitService = habitService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        logger.info("HabitEventFacade :: list(userId = {})", userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        List<HabitEventModel> models = service.list(user);

        if (models.isEmpty()) {
            responseStatus = ResponseStatus.XxL1;
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("HabitEventFacade :: list(userId = {}) = {}", userId, response);
            return response;
        }

        models.forEach((h) -> h.getHabit().getUser().setPassword(""));

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(models)
                .build();
        logger.info("HabitEventFacade :: list(userId = {}) = {}", userId, response);
        return response;
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    public Response create(HabitEventModel model, long userId) {
        logger.info("HabitEventFacade :: create(model = {}, userId = {})", model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);

        // validate if habit belongs to user
        if (!habitService.existsByIdAndUser(model.getHabit().getId(), user)) {
            responseStatus = ResponseStatus.HeX1;
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("HabitEventFacade :: create(model = {}, userId) = {}) = {}}", model, userId, response);
            return response;
        }

        // assign category from db based on model's category id
        model.setHabit(habitService.read(model.getHabit().getId()));


        model.setId(0);
        HabitEventModel createdModel = service.create(model);
        createdModel.getHabit().getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
        logger.info("HabitEventFacade :: create(model = {}, userId) = {}) = {}}", model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- R E A D
    public Response read(long id, long userId) {
        logger.info("HabitEventFacade :: read(id = {}, userId = {})", id, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);

        try {
            // validate id
            if (!service.existsById(id)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate user
            if (user.getId() != service.read(id).getHabit().getUser().getId()) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("HabitEventFacade :: read(id = {}, userId = {}) = {}}", id, userId, response);
            return response;
        }

        HabitEventModel readModel = service.read(id);
        readModel.getHabit().getUser().setPassword("");

        Response response = builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
        logger.info("HabitEventFacade :: read(id = {}, userId = {}) = {}}", id, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- U P D A T E
    public Response update(long id, HabitEventModel model, long userId) {
        logger.info("HabitEventFacade :: update(id = {}, model = {}, userId = {})", id, model, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);

        try {
            // validate id
            if (!service.existsById(id)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate user
            if (user.getId() != service.read(id).getHabit().getUser().getId()) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            //validate habit
            if (!habitService.existsByIdAndUser(model.getHabit().getId(), user)) {
                responseStatus = ResponseStatus.HeX1;
                throw new ValidationException();
            }
            // assign habit from db based on model's habit id
            model.setHabit(habitService.read(model.getHabit().getId()));
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("HabitEventFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
            return response;
        }
        HabitEventModel updatedModel = service.update(id, model);
        updatedModel.getHabit().getUser().setPassword("");

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(updatedModel)
                .build();
        logger.info("HabitEventFacade :: update(id = {}, model = {}, userId = {}) = {}", id, model, userId, response);
        return response;
    }

    // ------------------------------------------------------------------------------------------- D E L E T E
    public Response delete(long id, long userId) {
        logger.info("HabitEventFacade :: delete(id = {}, userId = {})", id, userId);
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        try {
            // validate id
            if (!service.existsById(id)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate user
            if (user.getId() != service.read(id).getHabit().getUser().getId()) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("HabitEventFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
            return response;
        }

        if (!service.delete(id)) {
            responseStatus = ResponseStatus.XxX2;
        }

        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
        logger.info("HabitEventFacade :: delete(id = {}, userId = {}) = {}", id, userId, response);
        return response;
    }
}

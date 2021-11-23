package pl.gawor.tayckner.taycknerbackend.service.facade;

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

    public ScheduleFacade(ScheduleService service, UserService userService) {
        this.service = service;
        this.userService = userService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;
        UserModel user = userService.read(userId);
        List<ScheduleModel> models = service.list(user);

        if (models.isEmpty()) {
            responseStatus = ResponseStatus.MAL1;
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }
        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(models)
                .build();
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    public Response create(ScheduleModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;

        UserModel user = userService.read(userId);
        try {
            // validate times
            if (model.getStartTime().isAfter(model.getEndTime())) {
                responseStatus = ResponseStatus.MAC3;
                throw new ValidationException();
            }
            // validate duration
            if (model.getDuration() < 0) {
                responseStatus = ResponseStatus.MSC2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        model.setId(0);
        model.setUser(user);
        ScheduleModel createdModel = service.create(model);
        createdModel.getUser().setPassword("");

        return builder
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
    }

    // ------------------------------------------------------------------------------------------- R E A D
    public Response read(long id, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;

        UserModel user = userService.read(userId);

        try {
            // validate user and id
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.MAR1;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        ScheduleModel readModel = service.read(id);
        readModel.getUser().setPassword("");

        return builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
    }

    // ------------------------------------------------------------------------------------------- U P D A T E
    public Response update(long id, ScheduleModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;
        UserModel user = userService.read(userId);

        try {
            // validate user and id
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.MAR1;
                throw new ValidationException();
            }
            // validate times
            if (model.getStartTime().isAfter(model.getEndTime())) {
                responseStatus = ResponseStatus.MAC3;
                throw new ValidationException();
            }
            // validate duration
            if (model.getDuration() < 0) {
                responseStatus = ResponseStatus.MSC2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }
        model.setUser(user);
        ScheduleModel updatedModel = service.update(id, model);

        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(updatedModel)
                .build();
    }

    // ------------------------------------------------------------------------------------------- D E L E T E
    public Response delete(long id, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;
        UserModel user = userService.read(userId);
        try {
            // validate user and id
            if (!service.existsByIdAndUser(id, user)) {
                responseStatus = ResponseStatus.MAR1;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        if (!service.delete(id)) {
            responseStatus = ResponseStatus.MAR1;
        }

        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
    }
}

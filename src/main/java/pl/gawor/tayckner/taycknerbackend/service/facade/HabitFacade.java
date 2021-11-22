package pl.gawor.tayckner.taycknerbackend.service.facade;

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

    public HabitFacade(HabitService service, UserService userService) {
        this.service = service;
        this.userService = userService;
        builder = new Response.Builder();
    }


    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;
        UserModel user = userService.read(userId);
        List<HabitModel> models = service.list(user);

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
    public Response create(HabitModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;

        UserModel user = userService.read(userId);
        try {
            // validate name
            if (service.existByName(model.getName(), user)) {
                responseStatus = ResponseStatus.MHC1;
                throw new ValidationException();
            }
            // validate color
            if (!Color.validate(model.getColor()) || model.getColor().length() > 7) {
                responseStatus = ResponseStatus.MAC2;
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
        HabitModel createdModel = service.create(model);
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

        HabitModel readModel = service.read(id);
        readModel.getUser().setPassword("");

        return builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
    }
}

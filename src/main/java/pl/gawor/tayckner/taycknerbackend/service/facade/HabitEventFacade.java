package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
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

    public HabitEventFacade(HabitEventService service, UserService userService, HabitService habitService) {
        this.service = service;
        this.userService = userService;
        this.habitService = habitService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;
        UserModel user = userService.read(userId);
        List<HabitEventModel> models = service.list(user);

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
    public Response create(HabitEventModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.M0;
        UserModel user = userService.read(userId);

        // validate if habit belongs to user
        if (!habitService.existsByIdAndUser(model.getHabit().getId(), user)) {
            responseStatus = ResponseStatus.MAC4;
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        // assign category from db based on model's category id
        model.setHabit(habitService.read(model.getHabit().getId()));


        model.setId(0);
        HabitEventModel createdModel = service.create(model);
        createdModel.getHabit().getUser().setPassword("");

        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
    }
}

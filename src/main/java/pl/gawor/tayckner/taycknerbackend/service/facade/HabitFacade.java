package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.CategoryModel;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
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
}

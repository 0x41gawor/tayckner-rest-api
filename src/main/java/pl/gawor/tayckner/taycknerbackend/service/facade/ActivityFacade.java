package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.ValidationException;
import pl.gawor.tayckner.taycknerbackend.service.service.ActivityService;
import pl.gawor.tayckner.taycknerbackend.service.service.CategoryService;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;

import java.util.List;

/**
 * Facade class for `Activity` endpoints.
 */
@Component
public class ActivityFacade {
    private final ActivityService service;
    private final UserService userService;
    private final CategoryService categoryService;

    private final Response.Builder builder;

    public ActivityFacade(ActivityService service, UserService userService, CategoryService categoryService) {
        this.service = service;
        this.userService = userService;
        this.categoryService = categoryService;
        builder = new Response.Builder();
    }

    // ------------------------------------------------------------------------------------------ L I S T
    public Response list(long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        List<ActivityModel> models = service.list(user);
        if (models.isEmpty()) {
            responseStatus = ResponseStatus.XxL1;
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
    public Response create(ActivityModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);

        // validate if category belongs to user
        if (!categoryService.existsByIdAndUser(model.getCategory().getId(), user)) {
            responseStatus = ResponseStatus.AcX1;
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        // assign category from db based on model's category id
        model.setCategory(categoryService.read(model.getCategory().getId()));

        // validate activity itself
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
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        model.setId(0);
        ActivityModel createdModel = service.create(model);
        createdModel.getCategory().getUser().setPassword("");

        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(createdModel)
                .build();
    }

    // ------------------------------------------------------------------------------------------- R E A D
    public Response read(long id, long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);

        try {
            // validate id
            if (!service.existsById(id)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate user
            if (user.getId() != service.read(id).getCategory().getUser().getId()) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        ActivityModel readModel = service.read(id);
        readModel.getCategory().getUser().setPassword("");

        return builder
                .setResponseStatus(responseStatus)
                .setContent(readModel)
                .build();
    }

    // ------------------------------------------------------------------------------------------- U P D A T E
    public Response update(long id, ActivityModel model, long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;

        UserModel user = userService.read(userId);

        try {
            // validate id
            if (!service.existsById(id)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate user
            if (user.getId() != service.read(id).getCategory().getUser().getId()) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            //validate category
            if (!categoryService.existsByIdAndUser(model.getCategory().getId(), user)) {
                responseStatus = ResponseStatus.AcX1;
                throw new ValidationException();
            }
            // assign category from db based on model's category id
            model.setCategory(categoryService.read(model.getCategory().getId()));
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
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }
        ActivityModel updatedModel = service.update(id, model);


        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(updatedModel)
                .build();
    }

    // ------------------------------------------------------------------------------------------- D E L E T E
    public Response delete(long id, long userId) {
        ResponseStatus responseStatus = ResponseStatus.XxX0;
        UserModel user = userService.read(userId);
        try {
            // validate id
            if (!service.existsById(id)) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
            // validate user
            if (user.getId() != service.read(id).getCategory().getUser().getId()) {
                responseStatus = ResponseStatus.XxX2;
                throw new ValidationException();
            }
        } catch (ValidationException e) {
            return builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
        }

        if (!service.delete(id)) {
            responseStatus = ResponseStatus.XxX2;
        }

        return builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
    }
}

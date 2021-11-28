package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.util.ValidationException;
import pl.gawor.tayckner.taycknerbackend.service.service.UserService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;
import pl.gawor.tayckner.taycknerbackend.web.response.ResponseStatus;
import org.mindrot.jbcrypt.BCrypt;
import pl.gawor.tayckner.taycknerbackend.web.security.JWTGenerator;

/**
 * Facade class for `User` endpoints.
 */
@Component
public class UserFacade {

    private final UserService service;
    private final JWTGenerator jwtGenerator;

    private final Logger logger = LoggerFactory.getLogger(UserFacade.class);


    public UserFacade(UserService service, JWTGenerator jwtGenerator) {
        this.service = service;
        this.jwtGenerator = jwtGenerator;
    }

    // -------------------------------------------------------------------------------------- R E G I S T E R
    public Response register(UserModel model) {
        logger.info("UserFacade :: register(model = {})", model);

        ResponseStatus responseStatus = ResponseStatus.R0;

        // validation (as for now, only these fields are validated, later more conditions will be added
        try {
            // valid if email is not taken
            if (service.existsByEmail(model.getEmail())) {
                responseStatus = ResponseStatus.R2;
                throw new ValidationException();
            }
            // valid if username is not taken
            if (service.existsByUsername(model.getUsername())) {
                responseStatus = ResponseStatus.R1;
                throw new ValidationException();
            }
            // valid if password is longer than 4 characters
            if (model.getPassword().length() < 5) {
                responseStatus = ResponseStatus.R4;
                throw new ValidationException();
            }
        } // if we catch any validation error return;
        catch (ValidationException e) {
            Response.Builder builder = new Response.Builder();
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("UserFacade :: register(model = {}) = {}", model, response);
            return response;
        }
        String hashedPassword = BCrypt.hashpw(model.getPassword(), BCrypt.gensalt(10));
        model.setPassword(hashedPassword);

        service.create(model);

        Response.Builder builder = new Response.Builder();
        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .build();
        logger.info("UserFacade :: register(model = {}) = {}", model, response);
        return response;
    }

    // ---------------------------------------------------------------------------------------- L O G I N
    public Response login(String username, String password) {
        logger.info("UserFacade :: login(username = {}, password = {})", username, password);
        ResponseStatus responseStatus = ResponseStatus.L0;

        try {
            if (!service.existsByUsername(username)) {
                responseStatus = ResponseStatus.L1;
                throw new ValidationException();
            }
            UserModel user = service.findByUsername(username);

            if (!BCrypt.checkpw(password, user.getPassword())) {
                responseStatus = ResponseStatus.L2;
            }
        } catch (ValidationException e) {
            Response.Builder builder = new Response.Builder();
            Response response = builder
                    .clear()
                    .setResponseStatus(responseStatus)
                    .build();
            logger.info("UserFacade :: login(username = {}, password = {}) = {}", username, password, response);
            return response;
        }
        UserModel user = service.findByUsername(username);
        String jwt = jwtGenerator.generateJWT(user);

        Response.Builder builder = new Response.Builder();
        Response response = builder
                .clear()
                .setResponseStatus(responseStatus)
                .setContent(jwt)
                .build();
        logger.info("UserFacade :: login(username = {}, password = {}) = {}", username, password, response);
        return response;
    }
}

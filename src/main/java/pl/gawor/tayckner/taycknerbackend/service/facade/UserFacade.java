package pl.gawor.tayckner.taycknerbackend.service.facade;

import org.springframework.stereotype.Component;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
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

    public UserFacade(UserService service, JWTGenerator jwtGenerator) {
        this.service = service;
        this.jwtGenerator = jwtGenerator;
    }

    // -------------------------------------------------------------------------------------- R E G I S T E R
    public Response register(UserModel model) {
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
            return builder
                    .setResponseStatus(responseStatus)
                    .build();
        }
        String hashedPassword = BCrypt.hashpw(model.getPassword(), BCrypt.gensalt(10));
        model.setPassword(hashedPassword);

        service.create(model);

        Response.Builder builder = new Response.Builder();
        return builder
                .setResponseStatus(responseStatus)
                .build();
    }
    // ---------------------------------------------------------------------------------------- L O G I N
    public Response login(String username, String password) {
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
            return builder
                    .setResponseStatus(responseStatus)
                    .build();
        }
        UserModel user = service.findByUsername(username);
        String jwt = jwtGenerator.generateJWT(user);

        Response.Builder builder = new Response.Builder();
        return builder
                .setResponseStatus(responseStatus)
                .setContent(jwt)
                .build();
    }

}

package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.UserFacade;
import pl.gawor.tayckner.taycknerbackend.service.service.HabitService;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;

import java.util.Map;

/**
 * Controller class for registering and logging users.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserFacade facade;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserFacade facade, HabitService habitService) {
        this.facade = facade;
    }

    // -------------------------------------------------------------------------------------- R E G I S T E R
    @PostMapping(
            value = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserModel model) {
        logger.info("UserController :: register(model = {})", model);
        Response response = facade.register(model);
        logger.info("UserController :: register(model = {}) = {}", model, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- L O G I N
    @PostMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        logger.info("UserController :: login(credentials = {})", credentials);
        String username = credentials.get("username");
        String password = credentials.get("password");
        Response response = facade.login(username, password);
        logger.info("UserController :: login(credentials = {}) = {}", credentials, response);
        return response.getResponseEntity();
    }
}

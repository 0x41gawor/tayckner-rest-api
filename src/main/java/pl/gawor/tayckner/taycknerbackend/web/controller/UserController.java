package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.UserFacade;
import pl.gawor.tayckner.taycknerbackend.service.service.HabitService;

import java.util.Map;

/**
 * Controller class for registering and logging users.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserFacade facade;

    public UserController(UserFacade facade, HabitService habitService) {
        this.facade = facade;
    }

    // -------------------------------------------------------------------------------------- R E G I S T E R
    @PostMapping(
            value = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> register(@RequestBody UserModel model) {
        return facade.register(model).getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- L O G I N
    @PostMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        return facade.login(username, password).getResponseEntity();
    }
}

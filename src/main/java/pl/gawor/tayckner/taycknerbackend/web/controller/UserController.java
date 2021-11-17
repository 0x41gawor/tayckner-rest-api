package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gawor.tayckner.taycknerbackend.core.model.UserModel;

import java.util.Map;

/**
 * Controller class for registering and logging users.
 */
@RestController
@RequestMapping("api/users")
public class UserController {

    // -------------------------------------------------------------------------------------- R E G I S T E R
    @PostMapping(
            value = "/register",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String register(@RequestBody UserModel model) {
        return "register";
    }
    // -------------------------------------------------------------------------------------- L O G I N
    @PostMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String login(@RequestBody Map<String, String> credentials) {
       return "login";
    }

}

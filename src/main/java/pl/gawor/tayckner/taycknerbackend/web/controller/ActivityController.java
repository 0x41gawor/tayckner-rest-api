package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.ActivityFacade;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller class for CRUDing Activities.
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityFacade facade;

    public ActivityController(ActivityFacade facade) {
        this.facade = facade;
    }

    // -------------------------------------------------------------------------------------- L I S T
    @GetMapping(
            value = "",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> list(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        return facade.list(userId).getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @PostMapping(
            value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest request, @RequestBody ActivityModel model) {
       int userId = (int) request.getAttribute("userId");
       return facade.create(model, userId).getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- R E A D
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> read(HttpServletRequest request, @PathVariable(name = "id") long id) {
        int userId = (int) request.getAttribute("userId");
        return facade.read(id, userId).getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String update(@PathVariable(name = "id") long id, @RequestBody ActivityModel model) {
        return "update";
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @DeleteMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String delete(@PathVariable(name = "id") long id) {
        return "delete";
    }

}
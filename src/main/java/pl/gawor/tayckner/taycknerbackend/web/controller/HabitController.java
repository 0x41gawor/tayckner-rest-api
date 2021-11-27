package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.HabitFacade;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller class for CRUDing Habits.
 */
@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitFacade facade;

    public HabitController(HabitFacade facade) {
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
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest request, @RequestBody HabitModel model) {
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
    public ResponseEntity<Map<String, Object>> update(HttpServletRequest request, @PathVariable(name = "id") long id, @RequestBody HabitModel model) {
        int userId = (int) request.getAttribute("userId");
        return facade.update(id, model, userId).getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @DeleteMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> delete(HttpServletRequest request, @PathVariable(name = "id") long id) {
        int userId = (int) request.getAttribute("userId");
        return facade.delete(id, userId).getResponseEntity();
    }

}

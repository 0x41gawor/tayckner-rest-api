package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;

/**
 * Controller class for CRUDing Activities.
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    // -------------------------------------------------------------------------------------- L I S T
    @GetMapping(
            value = "",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String list() {
        return "list";
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @PostMapping(
            value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String create(@RequestBody ActivityModel model) {
        return "create";
    }

    // -------------------------------------------------------------------------------------- R E A D
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String read(@PathVariable(name = "id") long id) {
        return "read";
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
package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.ActivityModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.ActivityFacade;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller class for CRUDing Activities.
 */
@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityFacade facade;

    private final Logger logger = LoggerFactory.getLogger(ActivityController.class);


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
        logger.info("ActivityController :: list(userId = {})", userId);
        Response response = facade.list(userId);
        logger.info("ActivityController :: list(userId = {}) = {}", userId, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @PostMapping(
            value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest request, @RequestBody ActivityModel model) {
        int userId = (int) request.getAttribute("userId");
        logger.info("ActivityController :: create(userId = {}, model = {})", userId, model);
        Response response = facade.create(model, userId);
        logger.info("ActivityController :: create(userId = {}, model = {}) = {}", userId, model, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- R E A D
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> read(HttpServletRequest request, @PathVariable(name = "id") long id) {
        int userId = (int) request.getAttribute("userId");
        logger.info("ActivityController :: read(userId = {}, id = {})", userId, id);
        Response response = facade.read(id, userId);
        logger.info("ActivityController :: read(userId = {}, id = {}) = {}", userId, id, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> update(HttpServletRequest request, @PathVariable(name = "id") long id, @RequestBody ActivityModel model) {
        int userId = (int) request.getAttribute("userId");
        logger.info("ActivityController :: update(userId = {}, id = {}, model = {})", userId, id, model);
        Response response = facade.update(id, model, userId);
        logger.info("ActivityController :: update(userId = {}, id = {}, model = {}) = {}", userId, id, model, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @DeleteMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> delete(HttpServletRequest request, @PathVariable(name = "id") long id) {
        int userId = (int) request.getAttribute("userId");
        logger.info("ActivityController :: update(userId = {}, id = {})", userId, id);
        Response response = facade.delete(id, userId);
        logger.info("ActivityController :: update(userId = {}, id = {}) = {}}", userId, id, response);
        return response.getResponseEntity();
    }

}
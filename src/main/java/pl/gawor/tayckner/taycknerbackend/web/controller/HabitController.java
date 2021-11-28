package pl.gawor.tayckner.taycknerbackend.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gawor.tayckner.taycknerbackend.core.model.HabitModel;
import pl.gawor.tayckner.taycknerbackend.service.facade.HabitFacade;
import pl.gawor.tayckner.taycknerbackend.web.response.Response;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Controller class for CRUDing Habits.
 */
@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitFacade facade;

    private final Logger logger = LoggerFactory.getLogger(HabitController.class);


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
        logger.info("HabitController :: list(userId = {})", userId);
        Response response = facade.list(userId);
        logger.info("HabitController :: list(userId = {}) = {}", userId, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- C R E A T E
    @PostMapping(
            value = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest request, @RequestBody HabitModel model) {
        int userId = (int) request.getAttribute("userId");
        logger.info("HabitController :: create(userId = {}, model = {})", userId, model);
        Response response = facade.create(model, userId);
        logger.info("HabitController :: create(userId = {}, model = {}) = {}", userId, model, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- R E A D
    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> read(HttpServletRequest request, @PathVariable(name = "id") long id) {
        int userId = (int) request.getAttribute("userId");
        logger.info("HabitController :: read(userId = {}, id = {})", userId, id);
        Response response = facade.read(id, userId);
        logger.info("HabitController :: read(userId = {}, id = {}) = {}", userId, id, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- U P D A T E
    @PutMapping(
            value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> update(HttpServletRequest request, @PathVariable(name = "id") long id, @RequestBody HabitModel model) {
        int userId = (int) request.getAttribute("userId");
        logger.info("HabitController :: update(userId = {}, id = {}, model = {})", userId, id, model);
        Response response = facade.update(id, model, userId);
        logger.info("HabitController :: update(userId = {}, id = {}, model = {}) = {}", userId, id, model, response);
        return response.getResponseEntity();
    }

    // -------------------------------------------------------------------------------------- D E L E T E
    @DeleteMapping(
            value = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Map<String, Object>> delete(HttpServletRequest request, @PathVariable(name = "id") long id) {
        int userId = (int) request.getAttribute("userId");
        logger.info("HabitController :: delete(userId = {}, id = {})", userId, id);
        Response response = facade.delete(id, userId);
        logger.info("HabitController :: delete(userId = {}, id = {}) = {}}", userId, id, response);
        return response.getResponseEntity();
    }

}

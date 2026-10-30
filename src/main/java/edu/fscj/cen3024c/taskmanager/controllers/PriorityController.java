// PriorityController.java
// D. Singletary
// 11/20/25
// Controller for Priority entity (read-only)

package edu.fscj.cen3024c.taskmanager.controllers;

import edu.fscj.cen3024c.taskmanager.entities.Priority;
import edu.fscj.cen3024c.taskmanager.services.PriorityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priorities")
@Tag(name = "Priorities", description = "Read-only lookup endpoints for task priority levels")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    // READ ONLY

    @Operation(summary = "Get all priorities",
            description = "Returns all available priority values (e.g., LOW, MEDIUM, HIGH).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Priorities returned successfully")
    })
    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping
    public List<Priority> getAllPriorities() {
        return priorityService.findAll();
    }

    @Operation(summary = "Get priority by id",
            description = "Returns a single priority by its id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Priority returned successfully"),
            @ApiResponse(responseCode = "404", description = "Priority not found")
    })
    @GetMapping("/{id}")
    public Priority getPriorityById(@PathVariable Integer id) {
        return priorityService.findById(id);
    }
}

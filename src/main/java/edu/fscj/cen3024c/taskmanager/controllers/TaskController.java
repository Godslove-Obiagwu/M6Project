// TaskController.java
// D. Singletary
// 11/20/25
// Task controller for task manager application

package edu.fscj.cen3024c.taskmanager.controllers;

import edu.fscj.cen3024c.taskmanager.dto.CreateTaskRequestDTO;
import edu.fscj.cen3024c.taskmanager.dto.TaskDTO;
import edu.fscj.cen3024c.taskmanager.dto.UpdateTaskRequestDTO;
import edu.fscj.cen3024c.taskmanager.entities.Priority;
import edu.fscj.cen3024c.taskmanager.exceptions.PriorityNotFoundException;
import edu.fscj.cen3024c.taskmanager.repositories.PriorityRepository;
import edu.fscj.cen3024c.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PriorityRepository priorityRepository;

    // ===== READ endpoints return DTOs =====

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Integer id) {
        return taskService.findById(id);
    }

    // ===== WRITE endpoints accept DTOs and return DTOs =====

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @PostMapping
    public TaskDTO createTask(@Valid @RequestBody CreateTaskRequestDTO request) {
        Priority priority = resolvePriority(request.getPriorityId());
        return taskService.createTask(request, priority);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Integer id, @Valid @RequestBody UpdateTaskRequestDTO request) {
        Priority priority = resolvePriority(request.getPriorityId());
        return taskService.updateTask(id, request, priority);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ===== Associate a User with a Task =====
    @PostMapping("/{taskId}/users/{userId}")
    public ResponseEntity<Void> assignUserToTask(@PathVariable Integer taskId,
                                                 @PathVariable Integer userId) {
        taskService.assignUserToTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

    private Priority resolvePriority(Integer priorityId) {
        if (priorityId == null) {
            return null;
        }
        return priorityRepository.findById(priorityId)
                .orElseThrow(() -> new PriorityNotFoundException(priorityId));
    }
}

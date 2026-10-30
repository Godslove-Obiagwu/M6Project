// SubtaskController.java
// D. Singletary
// 11/20/25
// Subtask controller for task manager application

package edu.fscj.cen3024c.taskmanager.controllers;

import edu.fscj.cen3024c.taskmanager.dto.CreateSubtaskRequestDTO;
import edu.fscj.cen3024c.taskmanager.dto.SubtaskDTO;
import edu.fscj.cen3024c.taskmanager.dto.UpdateSubtaskRequestDTO;
import edu.fscj.cen3024c.taskmanager.services.SubtaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

    @Autowired
    private SubtaskService subtaskService;

    // ===== READ endpoints return DTOs =====

    @GetMapping
    public List<SubtaskDTO> getAllSubtasks() {
        return subtaskService.findAll();
    }

    @CrossOrigin(origins = {"http://example.com", "http://localhost"})
    @GetMapping("/{id}")
    public SubtaskDTO getSubtaskById(@PathVariable Integer id) {
        return subtaskService.findById(id);
    }

    // ===== WRITE endpoints accept request DTOs and return response DTOs =====

    @PostMapping
    public SubtaskDTO createSubtask(@Valid @RequestBody CreateSubtaskRequestDTO request) {
        return subtaskService.createSubtask(request);
    }

    @PutMapping("/{id}")
    public SubtaskDTO updateSubtask(@PathVariable Integer id, @Valid @RequestBody UpdateSubtaskRequestDTO request) {
        return subtaskService.updateSubtask(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Integer id) {
        subtaskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

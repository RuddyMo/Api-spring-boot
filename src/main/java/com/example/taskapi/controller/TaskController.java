package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import com.example.taskapi.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    private final TaskRepository taskRepository;
    private List<Task> tasks = new ArrayList<>(List.of(new Task(1, "Apprendre Spring", false),
            new Task(2, "Apprendre Spring", true),
            new Task(3, "Apprendre Spring", false)));

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    ResponseEntity<Task> getTask(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tasks")
    Task newTask(@RequestBody Task newTask){
        return taskRepository.save(newTask);
    }
}

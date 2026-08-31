package com.example.taskapi.controller;

import com.example.taskapi.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {
    private List<Task> tasks = new ArrayList<>(List.of(new Task(1, "Apprendre Spring", false),
            new Task(2, "Apprendre Spring", true),
            new Task(3, "Apprendre Spring", false)));

    @GetMapping("/tasks")
    List<Task> getTasks() {
        return tasks;
    }

    @GetMapping("/tasks/{id}")
    Task getTask(@PathVariable int id) {
        for (Task task : tasks){
            if (task.getId() == id ) {
                return task;
            }
        }
        return null;
    }

    @PostMapping("/tasks")
    Task newTask(@RequestBody Task newTask){
        tasks.add(newTask);
        return newTask;
    }
}

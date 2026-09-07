package com.example.taskapi.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Optional;

import com.example.taskapi.model.Task;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM tasks",
                (rs, rowNum) -> {
                    return new Task(rs.getInt("id"), rs.getString("title"), rs.getBoolean("completed"));
                }
        );
    }

    public Optional<Task> findById(int id) {
        try {
             Task task = jdbcTemplate.queryForObject(
                    "SELECT * FROM tasks WHERE id = ?",
                    (rs, rowNum) -> {
                        return new Task(rs.getInt("id"), rs.getString("title"), rs.getBoolean("completed"));
                    }, id
            );
            return Optional.of(task);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Task save (Task task) {
        jdbcTemplate.update(
                "INSERT INTO tasks (title, completed) VALUES (?, ?)",
                task.getTitle(),
                task.isCompleted()
        );
        return task;
    }
}

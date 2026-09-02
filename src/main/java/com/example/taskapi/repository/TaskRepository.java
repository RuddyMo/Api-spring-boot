package com.example.taskapi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
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
}

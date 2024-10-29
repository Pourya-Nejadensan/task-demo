package org.task.taskbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.task.taskbackend.dto.TaskDTO;
import org.task.taskbackend.model.Task;
import org.task.taskbackend.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTaskById(@PathVariable(name = "id") String id) {
        taskService.deleteTaskById(id);
        return "Task with id: " + id + " deleted successfully";
    }
}

package org.task.taskbackend.service;

import org.task.taskbackend.dto.TaskDTO;
import org.task.taskbackend.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task createTask(TaskDTO taskDTO);
    void deleteTaskById(String id);
}

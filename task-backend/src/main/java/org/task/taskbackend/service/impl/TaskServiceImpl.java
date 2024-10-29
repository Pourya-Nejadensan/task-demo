package org.task.taskbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.taskbackend.dto.TaskDTO;
import org.task.taskbackend.model.Task;
import org.task.taskbackend.repository.TaskRepositrory;
import org.task.taskbackend.service.TaskService;
import org.task.taskbackend.util.IdService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepositrory taskRepositrory;
    private final IdService idService;

    @Override
    public List<Task> getAllTasks() {
        return taskRepositrory.findAll();
    }

    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task newTask = new Task(
                idService.generateId(),
                taskDTO.title(),
                taskDTO.completed()
        );
        return taskRepositrory.save(newTask);
    }

    @Override
    public void deleteTaskById(String id) {
        taskRepositrory.deleteById(id);
    }
}

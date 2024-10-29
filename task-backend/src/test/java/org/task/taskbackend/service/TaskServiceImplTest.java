package org.task.taskbackend.service;

import org.junit.jupiter.api.Test;
import org.task.taskbackend.dto.TaskDTO;
import org.task.taskbackend.model.Task;
import org.task.taskbackend.repository.TaskRepositrory;
import org.task.taskbackend.service.impl.TaskServiceImpl;
import org.task.taskbackend.util.IdService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    private final TaskRepositrory taskRepositrory = mock(TaskRepositrory.class);
    private final IdService idService = mock(IdService.class);
    private final TaskServiceImpl taskServiceImpl = new TaskServiceImpl(taskRepositrory, idService);

    @Test
    void getAllTasksTest_whenTasksAreEmpty_thenReturenTrue() {
        //GIVEN
        when(taskRepositrory.findAll()).thenReturn(List.of());

        //WHEN
        List<Task> tasks = taskServiceImpl.getAllTasks();

        //THEN
        assertTrue(tasks.isEmpty());
    }

    @Test
    void getAllTasksTest_whenTasksAreNotEmpty_thenReturenTrue() {
        //GIVEN
        List<Task> mockTasks = List.of(
                new Task(
                        "1",
                        "title",
                        false
                ),
                new Task(
                        "2",
                        "title",
                        false
                )
        );
        when(taskRepositrory.findAll()).thenReturn(mockTasks);

        //WHEN
        List<Task> tasks = taskServiceImpl.getAllTasks();

        //THEN
        verify(taskRepositrory).findAll();
        assertEquals(tasks, mockTasks);
    }

    @Test
    void createTaskTest_whenTaskIsCreated_thenReturnCreatedTask() {
        //GIVEN
        Task expectedTask = new Task(
                "1",
                "title",
                false
        );
        when(idService.generateId()).thenReturn(expectedTask.id());
        when(taskRepositrory.save(expectedTask)).thenReturn(expectedTask);

        TaskDTO taskDTO = new TaskDTO(
                expectedTask.title(),
                expectedTask.completed()
        );
        //WHEN
        Task createdTask = taskServiceImpl.createTask(taskDTO);

        //THEN
        verify(idService).generateId();
        verify(taskRepositrory).save(expectedTask);
        assertEquals(expectedTask, createdTask);
    }

    @Test
    void deleteTaskById_whenTaskExists_thenTaskIsDeleted(){
        //GIVEN
        doNothing().when(taskRepositrory).deleteById("1");

        //WHEN
        taskRepositrory.deleteById("1");

        //THEN
        verify(taskRepositrory).deleteById("1");
    }
}

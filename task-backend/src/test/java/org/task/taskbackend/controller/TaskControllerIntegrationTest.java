package org.task.taskbackend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.task.taskbackend.model.Task;
import org.task.taskbackend.repository.TaskRepositrory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TaskRepositrory taskRepositrory;

    @Test
    @DirtiesContext
    void testGetAllTasks() throws Exception {
        //GIVEN
        taskRepositrory.save(new Task(
                "1",
                "Title1",
                true
        ));

        //WHEN
        mockMvc.perform(get("/api/task/all"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "1",
                                "title": "Title1",
                                "completed": true
                            }
                        ]
                        """));
    }

    @Test
    @DirtiesContext
    void createTasks() throws Exception {
        // given
        String taskDTOJson = """
                {
                    "title": "My First Post",
                    "completed": true
                }
                """;

        // when
        mockMvc.perform(post("/api/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskDTOJson))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("My First Post"))
                .andExpect(jsonPath("$.completed").value(true));
    }

    @Test
    @DirtiesContext
    void deletePostById() throws Exception {
        //GIVEN
        taskRepositrory.save(
                new Task(
                        "1",
                        "Title1",
                        true
                ));

        //WHEN
        mockMvc.perform(delete("/api/task/delete/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().string("Task with id: 1 deleted successfully"));

        //THEN
        mockMvc.perform(get("/api/task/all"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}

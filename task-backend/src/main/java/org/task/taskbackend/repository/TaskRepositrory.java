package org.task.taskbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.task.taskbackend.model.Task;

public interface TaskRepositrory extends MongoRepository<Task, String> {
}

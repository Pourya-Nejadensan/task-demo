package org.task.taskbackend.model;

import lombok.With;

@With
public record Task(
        String id,
        String title,
        boolean completed
) {
}

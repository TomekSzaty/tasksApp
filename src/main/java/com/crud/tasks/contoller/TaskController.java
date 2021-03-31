package com.crud.tasks.contoller;

import com.crud.tasks.domain.TaskDto;

import java.util.ArrayList;
import java.util.List;

public class TaskController {


    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }
}

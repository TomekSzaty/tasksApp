package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_Task", "test_content");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, mappedTask.getId());
        assertEquals("test_Task", mappedTask.getTitle());
        assertEquals("test_content", mappedTask.getContent());
    }
    @Test
    void testMapToTaskDto() {
        //given
        Task task = new Task(1L, "test title", "test content");
        //when
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //then
        assertEquals(1L, mappedTaskDto.getId());
        assertEquals("test title", mappedTaskDto.getTitle());
        assertEquals("test content", mappedTaskDto.getContent());
    }
    @Test
    void testMapToTaskDtoList() {
        //given
        Task task = new Task(1L, "test title", "test content");
        List<Task> taskList = List.of(task, task);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //then
        assertEquals(2, taskDtoList.size());
        taskDtoList.forEach(taskDto -> {
            assertEquals(1L, taskDto.getId());
            assertEquals("test title", taskDto.getTitle());
            assertEquals("test content", taskDto.getContent());
        });
    }
}

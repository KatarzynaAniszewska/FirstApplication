package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;
   Task task = new Task(1L,"Task_title","Task_content");
   TaskDto taskDto = new TaskDto(1L,"TaskDto_title","TaskDto_content");
   List<Task>taskList = new ArrayList<>();

   @Test
   public void mapToTaskTest() {
       //Given
       //When
       Task testTask = taskMapper.mapToTask(taskDto);
       //Then
       assertEquals("TaskDto_title",testTask.getTitle());
   }
   @Test
    public void mapToTaskDtoTest() {
        //Given
       taskList.add(task);
        //When
        TaskDto testTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals("Task_title",testTaskDto.getTitle());
    }
    @Test
    public void mapToTaskDtoListTest() {
        //Given
        //When
        List<TaskDto>taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals("Task_title",taskDtoList.get(0).getTitle());
    }
}

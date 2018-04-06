package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import sun.invoke.empty.Empty;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class DbServiceTestSuite {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    DbService dbService;
    Task savedTask;
    Long id;

    Task task = new Task("Test-task", "Test-content");

    @Before
    public void init() {
        savedTask = dbService.saveTask(task);
        id = savedTask.getId();
        System.out.println(savedTask.getTitle());
        System.out.println(id);
    }
    @Test
    public void saveTaskTest() {
        //Given
        //When
        //Then
        assertEquals("Test-task", savedTask.getTitle());
        //CleanUp
        dbService.deleteById(id);
    }
    @Test
    public void getTaskByIdTest() {
        //Given
        //When
        Task readTask = dbService.getTaskById(id);
        //Then
        assertEquals("Test-task", readTask.getTitle());
        //CleanUp
        dbService.deleteById(id);
    }
    @Test
    public void getAllTaskTest() {
        //Given
        //When
        List<Task> testList = dbService.getAllTasks();
        //Then
        assertNotNull(testList);
        //CleanUp
        dbService.deleteById(id);
    }
    @Test
    public void getTaskTask() {
        //Given
        //When
        Optional<Task> readTask = dbService.getTask(id);
        //Then
        assertEquals("Test-task", readTask.get().getTitle());
        //CleanUp
        dbService.deleteById(id);
    }
   @Test
    public void deleteByIdTest() {
       //Given
       //When
       //Then
       dbService.deleteById(id);
       assertNull(dbService.getTaskById(id));
   }
}




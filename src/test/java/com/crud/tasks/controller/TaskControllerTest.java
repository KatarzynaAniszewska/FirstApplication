package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    TaskMapper taskMapper;
    @MockBean
    DbService service;
    @Mock
    TaskController taskController;

    Task task = new Task(1L,"Task TestTitle", "Task_TestDesription");
    List<Task> taskList = new ArrayList<>();
    TaskDto taskDto = new TaskDto(1L, "TaskDto TestTitle", "TaskDto_TestDesription");
    List<TaskDto> taskDtoList = new ArrayList<>();

    @Test
    public void getTasksTest() throws Exception {
        taskList.add(task);
        taskDtoList.add(taskDto);
        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))//or isOK()
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void getTaskTest() throws Exception {
        when(service.getTask(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks")
                .param("taskId","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))//or isOK()
                .andExpect(jsonPath("$.title", is("TaskDto TestTitle")));
    }
    @Test
    public void deleteTaskTest() throws Exception {
        //Given
        //service.deleteById(1L);
        //When & Then
        mockMvc.perform(delete("/v1/tasks?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));//or isOK
    }
    @Test
    public void updateTaskTest() throws Exception {
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        mockMvc.perform(put(("/v1/tasks/"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));//or isOK()
    }
    @Test
    public void createTaskTest()throws Exception {
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        mockMvc.perform(post(("/v1/tasks"))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));//or isOK()
    }
}

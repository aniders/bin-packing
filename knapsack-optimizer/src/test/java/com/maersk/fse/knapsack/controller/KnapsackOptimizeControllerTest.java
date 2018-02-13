package com.maersk.fse.knapsack.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.fse.knpsack.controller.KnapsackOptimizerController;
import com.maersk.fse.knpsack.dto.Problem;
import com.maersk.fse.knpsack.dto.Task;
import com.maersk.fse.knpsack.dto.Task.Status;
import com.maersk.fse.knpsack.service.KnapsackService;
import com.maersk.fse.knpsack.util.RequestValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class KnapsackOptimizeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private KnapsackService knapsackServiceMock;

    @InjectMocks
    private KnapsackOptimizerController knapsackController;

    @Mock
    private RequestValidator requestValidatorMock;

    @Mock
    private Principal principal;

    BindingResult resultMock;

    @Mock
    private SecurityContext securityContext;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(knapsackController).build();
    }

    @Test
    public void getAllTasksTest() throws Exception {
        Task t1 = new Task();
        Task t2 = new Task();
        t2.setStatus(Status.STARTED.getValue());
        long started = System.currentTimeMillis() / 1000L;
        t2.getTimestamps().setStarted(started);
        List<Task> mock = Arrays.asList(new Task[] { t1, t2 });

        when(knapsackServiceMock.getAllTasks()).thenReturn(mock);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/tasks").accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[1].status", is("started")));

    }

    @Test
    public void getTaskNoHitsTest() throws Exception {
        List<Task> mock = new ArrayList<Task>();
        when(knapsackServiceMock.getAllTasks()).thenReturn(mock);
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/tasks").accept(APPLICATION_JSON)).andExpect(status().isNoContent());

    }

    @Test
    public void getTaskByIdTest() throws Exception {
        Task t2 = new Task();
        t2.setStatus(Status.STARTED.getValue());
        long started = System.currentTimeMillis() / 1000L;
        t2.getTimestamps().setStarted(started);
        when(knapsackServiceMock.getTaskById(t2.getTask())).thenReturn(t2);
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/" + t2.getTask()).accept(APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("task", is(t2.getTask())));

    }

     @Test
     public void getMessageByIdNoHitsTest() throws Exception {
     when(knapsackServiceMock.getTaskById("xcs23dfg")).thenReturn(null);
     mockMvc.perform(MockMvcRequestBuilders.get("/tasks/xcs23dfg").accept(APPLICATION_JSON)).andExpect(status().isNoContent());
    
     }

     @Test
     public void submitProblemTest() throws Exception {
     Problem request = Problem.builder().capacity(430).weights(new long[] {10, 20, 33}).values(new long[] {10, 3, 30}).build();
     String json = objectMapper.writeValueAsString(request);
     Task t1 = new Task();
     when(principal.getName()).thenReturn("user1@test.com");
     when(knapsackServiceMock.submitTask(request)).thenReturn(t1);
    
     mockMvc.perform(post("/tasks").principal(principal).content(json).contentType(APPLICATION_JSON)).andExpect(status().isCreated());
     }

   
}

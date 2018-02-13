package com.maersk.fse.knapsack.service;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.maersk.fse.knpsack.dao.TaskStatusDAO;
import com.maersk.fse.knpsack.dto.Task;
import com.maersk.fse.knpsack.dto.Task.Status;
import com.maersk.fse.knpsack.service.impl.KnapsackServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KnapsackServiceTest {

    @InjectMocks
    private KnapsackServiceImpl messageService;

    @Mock
    private TaskStatusDAO statusDAOMock;

    @BeforeClass
    public static void beforeClass() {

    }

    @Test
    public void getAllMessagesTest() {
        Task m1 = new Task();
        Task m2 = new Task();
        Task m3 = new Task();
        List<Task> mock = Arrays.asList(new Task[] { m1, m2, m3 });
        when(statusDAOMock.getAllTasks()).thenReturn(mock);
        List<Task> msgs = messageService.getAllTasks();
        assertNotNull(msgs);
        assertEquals(3, msgs.size());
        assertEquals(Status.SUBMITTED.getValue(), msgs.get(1).getStatus());
    }

}

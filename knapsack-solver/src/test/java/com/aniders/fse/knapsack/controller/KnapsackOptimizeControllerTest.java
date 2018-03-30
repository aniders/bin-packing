package com.aniders.fse.knapsack.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.security.Principal;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aniders.knpsack.solver.controller.KnapsackSolverController;
import com.aniders.knpsack.solver.dto.Problem;
import com.aniders.knpsack.solver.dto.Solution;
import com.aniders.knpsack.solver.service.KnapsackSolver;
import com.aniders.knpsack.solver.util.RequestValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class KnapsackOptimizeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private KnapsackSolver knapsackSolverMock;

	@InjectMocks
	private KnapsackSolverController knapsackController;
	@Mock
	private RequestValidator requestValidator;

	@Mock
	private Principal principal;

	@Mock
	private SecurityContext securityContext;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(knapsackController).setValidator(requestValidator).build();
	}

	@Test
	public void submitProblemTest() throws Exception {
		Problem request = Problem.builder().capacity(60).weights(new int[] { 10, 20, 33 })
				.values(new int[] { 10, 3, 30 }).build();
		String json = objectMapper.writeValueAsString(request);
		Solution t1 = new Solution(new int[] { 0, 2 }, 10);
		when(principal.getName()).thenReturn("app1");
		when(knapsackSolverMock.solve(any(Problem.class))).thenReturn(t1);

		mockMvc.perform(post("/solve").principal(principal).content(json).contentType(APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.items[0]", is(0)))
				.andExpect(jsonPath("$.items[1]", is(2)));

	}

}

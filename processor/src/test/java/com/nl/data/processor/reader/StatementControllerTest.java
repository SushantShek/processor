package com.nl.data.processor.reader;

import com.nl.data.processor.controller.StatementController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

public class StatementControllerTest {

	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new StatementController()).build();
	}

	@Ignore
	@Test
	public void giventUrl_whenGetRequest_thenFindGetResponse()
			throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get("/get");

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.string("GET Response");

		this.mockMvc.perform(builder).andExpect(contentMatcher)
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
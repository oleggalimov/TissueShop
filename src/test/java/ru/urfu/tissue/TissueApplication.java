package ru.urfu.tissue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.urfu.tissue.controllers.Common.Index;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class TissueApplication {

	@Autowired
	private MockMvc mockMvc;
	private Index indexController;


	@Before
	public void setUp() {
		indexController = new Index();
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}
	@Test
	public void testIndexController() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}
}
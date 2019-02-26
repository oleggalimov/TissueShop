package ru.urfu.tissue;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.urfu.tissue.controllers.Common.Index;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
@Epic("Эпик 1. Общее тестирование")
@Feature("Возврат всех представлений")


public class TissueApplication {

	@Autowired
	private static MockMvc mockMvc;

    @BeforeAll
	static void setUp() {
        System.out.println("Старт тестов");
        Index indexController = new Index();
		mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}

	@Test
    @Story("Пользователь просит главную страницу")
    @Description("Тест обращения к главному контроллеру")
    @DisplayName("Check index controller")
	void testIndexController() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
    @DisplayName("Check 404 error")
	void testPageNotFound() throws Exception {
        Assertions.assertTrue(true);
		mockMvc.perform(get("/notexistspage"))
				.andExpect(status().isNotFound())
//				.andExpect(view().name("errorpage"))
        ;
	}
}

package cl.ionix.user.controller;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class TestBackendApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	@Order(0)
	void getAllMethodTest() throws Exception {
		mockMvc.perform(get("/v1/user/")).andDo(print());
	}
	@Test
	@Order(1)
	void putMethodTest()throws Exception {
		mockMvc.perform(post("/v1/user/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"prueba1\", \"username\": \"userTest\",\"phone\": 2323, \"email\": \"email@prueba.com\"}")
						).andDo(print());
	}

}
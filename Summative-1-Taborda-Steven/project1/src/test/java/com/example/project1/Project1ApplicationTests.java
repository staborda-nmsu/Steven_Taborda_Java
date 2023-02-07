package com.example.project1;

import com.example.project1.MagicBallAPI.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class Project1ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Test
	public void testQuoteAPI() throws Exception {
		this.mockMvc.perform(get("/quote"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.author").exists())
				.andExpect(jsonPath("$.quote").exists());
	}

	@Test
	public void testWordAPI() throws Exception {
		this.mockMvc.perform(get("/word"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.word").exists())
				.andExpect(jsonPath("$.definition").exists());
	}

	@Test
	public void testMagicFilled() throws Exception {
		Question question = new Question(-1,"Will I be successful?");

		this.mockMvc.perform(post("/magic")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(question)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.question").exists())
				.andExpect(jsonPath("$.answer").exists());
	}

	@Test
	public void testMagicEmpty() throws Exception {
		this.mockMvc.perform(post("/magic"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.question").exists())
				.andExpect(jsonPath("$.answer").exists());
	}


}

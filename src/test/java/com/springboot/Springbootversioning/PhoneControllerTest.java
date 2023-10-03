package com.springboot.Springbootversioning;

import com.springboot.Springbootversioning.controllers.PhoneController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PhoneController.class)
public class PhoneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveNumber_withValidNumber() throws Exception {
        String validPhoneJson = "{\"phoneNumber\": \"1234567890\"}";
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/phone/savePhone")
                        .contentType("application/json")
                        .content(validPhoneJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Saved Num Succesfully!"));
    }

    @Test
    public void testSaveNumber_withInValidNumber() throws Exception {
        String invalidJson = "{\"phoneNumber\" : \"asasfasfad\"}";
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/phone/savePhone")
                        .contentType("application/json")
                        .content(invalidJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("please enter valid number"));
    }
}

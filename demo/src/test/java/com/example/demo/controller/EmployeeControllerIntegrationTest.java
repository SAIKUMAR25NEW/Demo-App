package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // GET /employees
    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk());    // response must be 200
    }

    // GET /employees/{id}
    @Test
    void testGetEmployeeById() throws Exception {
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk());
    }

    // POST /employees
    @Test
    void testAddEmployee() throws Exception {
        Employee newEmp = new Employee(3, "New User", "Developer", 55000);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmp)))
                .andExpect(status().isOk());   // Controller returns Employee, so 200 OK
    }




    // PUT /employees/{id}
    @Test
    void testUpdateEmployee() throws Exception {
        Employee updated = new Employee(1, "Sai Updated", "Lead", 70000);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk());
    }

    // DELETE /employees/{id}
    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }
}

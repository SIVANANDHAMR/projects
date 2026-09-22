package com.example.hrapp.controller;

import com.example.hrapp.entity.Employee;
import com.example.hrapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository repository;

    @Test
    void getsAnEmployeeWhenItExists() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setFirstName("Ada");
        employee.setLastName("Lovelace");
        employee.setEmail("ada@example.com");
        employee.setSalary(120000D);
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void returnsNotFoundWhenDeletingAMissingEmployee() throws Exception {
        when(repository.existsById(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/employees/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletesAnExistingEmployee() throws Exception {
        when(repository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());

        verify(repository).deleteById(1L);
    }
}


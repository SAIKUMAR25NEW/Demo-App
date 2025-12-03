package com.example.demo.service;

import com.example.demo.EmployeeService;
import java.util.List;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repo;

    @InjectMocks
    private EmployeeService service;

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = List.of(
                new Employee(1, "Sai", "Developer", 50000),
                new Employee(2, "Kumar", "Tester", 40000)
        );


        when(repo.findAll()).thenReturn(employees);

        List<Employee> result = service.getAllEmployees();

        assertEquals(2, result.size());
        verify(repo).findAll();
    }
    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee(1, "Sai", "Developer", 50000);

        when(repo.findById(1)).thenReturn(Optional.of(emp));

        Employee result = service.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("Sai", result.getName());
        verify(repo).findById(1);
    }
    @Test
    void testGetEmployeeById_NotFound() {
        when(repo.findById(99)).thenReturn(Optional.empty());

        Employee result = service.getEmployeeById(99);

        assertNull(result);
        verify(repo).findById(99);
    }
    @Test
    void testAddEmployee() {
        Employee emp = new Employee(0, "Alex", "Tester", 40000);

        when(repo.save(emp)).thenReturn(emp);

        Employee result = service.addEmployee(emp);

        assertEquals("Alex", result.getName());
        verify(repo).save(emp);
    }
    @Test
    void testUpdateEmployee() {
        Employee existing = new Employee(1, "OldName", "Dev", 30000);
        Employee updated = new Employee(1, "NewName", "QA", 60000);

        when(repo.findById(1)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        Employee result = service.updateEmployee(1, updated);

        assertEquals("NewName", result.getName());
        assertEquals("QA", result.getRole());
        assertEquals(60000, result.getSalary());
    }
    @Test
    void testUpdateEmployee_NotFound() {
        Employee updated = new Employee(1, "X", "Y", 1000);

        when(repo.findById(1)).thenReturn(Optional.empty());

        Employee result = service.updateEmployee(1, updated);

        assertNull(result);
    }
    @Test
    void testDeleteEmployee() {
        service.deleteEmployee(1);
        verify(repo).deleteById(1);
    }




}

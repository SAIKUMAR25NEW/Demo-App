package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // GET all employees
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    // GET by ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return service.getById(id);
    }

    // POST add new employee
    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        return service.add(employee);
    }

    // PUT update employee
    @PutMapping("/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee) {
        return service.update(id, employee);
    }

    // DELETE employee
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        boolean removed = service.delete(id);
        return removed ? "Employee deleted" : "Employee not found";
    }
}

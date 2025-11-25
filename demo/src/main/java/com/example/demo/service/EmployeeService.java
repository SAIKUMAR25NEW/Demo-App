package com.example.demo.service;


import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee(1, "Alice", "Developer", 70000));
        employees.add(new Employee(2, "Bob", "Tester", 55000));
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Employee getById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Employee add(Employee e) {
        employees.add(e);
        return e;
    }

    public Employee update(int id, Employee newData) {
        Employee existing = getById(id);
        if (existing != null) {
            existing.setName(newData.getName());
            existing.setRole(newData.getRole());
            existing.setSalary(newData.getSalary());
        }
        return existing;
    }

    public boolean delete(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }
}


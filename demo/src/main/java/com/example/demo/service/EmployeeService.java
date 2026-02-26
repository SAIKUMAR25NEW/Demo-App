package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;
    private final EmployeeEventProducer producer;

    public EmployeeService(EmployeeRepository repo,
                           EmployeeEventProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        Employee saved = repo.save(employee);

        // 🔥 KAFKA EVENT
     //   producer.publishEmployeeCreated(saved.getId());

        return saved;
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Optional<Employee> existing = repo.findById(id);

        if (existing.isPresent()) {
            Employee emp = existing.get();
            emp.setName(updatedEmployee.getName());
            emp.setRole(updatedEmployee.getRole());
            emp.setEmail(updatedEmployee.getEmail());
            emp.setSalary(updatedEmployee.getSalary());

            Employee saved = repo.save(emp);

            // 🔥 KAFKA EVENT (optional)
            producer.publishEmployeeCreated(saved.getId());

            return saved;
        }
        return null;
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }
}

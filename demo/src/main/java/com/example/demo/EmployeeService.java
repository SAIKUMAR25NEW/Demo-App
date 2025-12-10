package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        return repo.save(employee);
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Optional<Employee> existing = repo.findById(id);

        if (existing.isPresent()) {
            Employee emp = existing.get();
            emp.setName(updatedEmployee.getName());
            emp.setRole(updatedEmployee.getRole());
            emp.setEmail(updatedEmployee.getEmail());
            emp.setSalary(updatedEmployee.getSalary());
            return repo.save(emp);

        }
        return null;
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }
}

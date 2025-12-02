package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBTestRunner implements CommandLineRunner {

    private final EmployeeRepository repo;

    public DBTestRunner(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        repo.save(new Employee(0, "Alice", "Developer", 70000));
        repo.save(new Employee(0, "Bob", "Tester", 55000));
        System.out.println("Test data inserted into MySQL");
    }
}


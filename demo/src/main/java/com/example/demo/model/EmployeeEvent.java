package com.example.demo.model;

import java.time.LocalDateTime;

public class EmployeeEvent {

    private int employeeId;   // NOTE: int (matches Employee.id)
    private String action;
    private LocalDateTime timestamp;

    public EmployeeEvent() {
    }

    public EmployeeEvent(int employeeId, String action) {
        this.employeeId = employeeId;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

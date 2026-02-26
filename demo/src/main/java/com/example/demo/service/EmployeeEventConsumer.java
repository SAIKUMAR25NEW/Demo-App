package com.example.demo.service;

import com.example.demo.model.EmployeeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeEventConsumer {

    @KafkaListener(topics = "employee-events", groupId = "employee-group")
    public void consume(EmployeeEvent event) {
        System.out.println(
                "Kafka Event Received -> action=" + event.getAction()
                        + ", employeeId=" + event.getEmployeeId()
        );
    }
}

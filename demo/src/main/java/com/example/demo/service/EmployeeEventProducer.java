package com.example.demo.service;

import com.example.demo.model.EmployeeEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeEventProducer {

    private static final String TOPIC = "employee-events";

    private final KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

    public EmployeeEventProducer(KafkaTemplate<String, EmployeeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEmployeeCreated(int employeeId) {
        EmployeeEvent event = new EmployeeEvent(employeeId, "CREATED");
        kafkaTemplate.send(TOPIC, String.valueOf(employeeId), event);
    }

}

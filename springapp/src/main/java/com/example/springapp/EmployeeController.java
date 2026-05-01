package com.example.springapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/spring")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    // GET ALL EMPLOYEES
    @GetMapping("/employees")
    public ResponseEntity<?> getAll() {

        List<Employee> list = repo.findAll();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "data", list
            )
        );
    }

    // GET EMPLOYEE BY NAME
    @GetMapping("/employee")
    public ResponseEntity<?> getByName(@RequestParam String name) {

        Optional<Employee> emp = repo.findByName(name);

        if (emp.isEmpty()) {
            return ResponseEntity.status(404).body(
                Map.of(
                    "status", "error",
                    "message", "Employee not found"
                )
            );
        }

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "data", emp.get()
            )
        );
    }
}
package com.example.hrapp.controller;

import com.example.hrapp.entity.Employee;
import com.example.hrapp.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeRepository repo;
    public EmployeeController(EmployeeRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Employee> list() { return repo.findAll(); }

    @PostMapping
    public Employee create(@RequestBody Employee e) { return repo.save(e); }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee in) {
        return repo.findById(id).map(e -> {
            e.setFirstName(in.getFirstName());
            e.setLastName(in.getLastName());
            e.setEmail(in.getEmail());
            e.setSalary(in.getSalary());
            return ResponseEntity.ok(repo.save(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}

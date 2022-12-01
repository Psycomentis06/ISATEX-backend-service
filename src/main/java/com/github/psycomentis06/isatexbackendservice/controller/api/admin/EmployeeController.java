package com.github.psycomentis06.isatexbackendservice.controller.api.admin;

import com.github.psycomentis06.isatexbackendservice.entity.Employee;
import com.github.psycomentis06.isatexbackendservice.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createBrand(
            @RequestBody Employee employee
    ) {
        employeeService.createEmployee(employee);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Employee created successfully");
        resp.put("code", "200");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(
            @PathVariable int id
    ) {
        Optional<Employee> employeeOptional = employeeService.getEmployee(id);
        employeeOptional.orElseThrow(() -> {
            throw new EntityNotFoundException("Employee #" + id + " not found");
        });
        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Page<Employee>> getAll(
            @RequestParam(name = "s", required = false, defaultValue = "10") int s,
            @RequestParam(name = "p", required = false, defaultValue = "0") int p,
            @RequestParam(name = "q", required = false, defaultValue = "") String query
    ) {
        Pageable pageable = Pageable.ofSize(s);
        return new ResponseEntity<>(employeeService.getAll(query, pageable.withPage(p)), HttpStatus.OK);
    }
}

package com.bwina.springboot.controllers;

import com.bwina.springboot.exceptions.ResourceNotFoundException;
import com.bwina.springboot.models.Employee;
import com.bwina.springboot.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee updateEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());
        employeeRepo.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) throws ResourceNotFoundException {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        employeeRepo.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

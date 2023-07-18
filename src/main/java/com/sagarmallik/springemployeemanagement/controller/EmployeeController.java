package com.sagarmallik.springemployeemanagement.controller;

// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagarmallik.springemployeemanagement.model.Employee;
import com.sagarmallik.springemployeemanagement.model.EmployeeRequestDTO;
import com.sagarmallik.springemployeemanagement.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
    String employeeId = employeeService.addEmployee(employeeRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).body(employeeId);
  }

  /*
   * @GetMapping
   * public ResponseEntity<List<Employee>> getAllEmployees() {
   * var employees = employeeService.getAllEmployees();
   * return ResponseEntity.status(HttpStatus.OK).body(employees);
   * }
   */

  @GetMapping("/{page}/{size}/{sortBy}")
  public ResponseEntity<Page<Employee>> getAllEmployees(@PathVariable int page, @PathVariable int size,
      @PathVariable String sortBy) {
    var employees = employeeService.getAllEmployees(page, size, sortBy);
    return ResponseEntity.status(HttpStatus.OK).body(employees);
  }

  @DeleteMapping("/{employeeId}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeId) {
    Employee employee = employeeService.getEmployeeById(employeeId);
    if (employee != null) {
      employeeService.deleteEmployee(employeeId);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @PutMapping("/{employeeId}")
  public ResponseEntity<Void> updateEmployee(@PathVariable String employeeId,
      @RequestBody EmployeeRequestDTO employeeRequestDTO) {
    var employee = employeeService.getEmployeeById(employeeId);
    if (employee != null) {
      employeeService.updateEmployee(employeeId, employeeRequestDTO);
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @GetMapping("/{employeeId}/manager/{level}")
  public ResponseEntity<Employee> getNthLevelManager(@PathVariable String employeeId, @PathVariable int level) {
    var employee = employeeService.getNthLevelManager(employeeId, level);
    if (employee == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(employee);
  }
}

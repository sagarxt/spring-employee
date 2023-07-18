package com.sagarmallik.springemployeemanagement.service;

// import java.util.List;

import org.springframework.data.domain.Page;

import com.sagarmallik.springemployeemanagement.model.Employee;
import com.sagarmallik.springemployeemanagement.model.EmployeeRequestDTO;

public interface EmployeeService {
  String addEmployee(EmployeeRequestDTO employeeRequestDTO);

  // List<Employee> getAllEmployees();

  Page<Employee> getAllEmployees(int page, int size, String sortBy);

  void deleteEmployee(String employeeId);

  void updateEmployee(String employeeId, EmployeeRequestDTO employeeRequestDTO);

  Employee getEmployeeById(String employeeId);

  Employee getNthLevelManager(String employeeId, int level);
}

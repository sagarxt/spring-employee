package com.sagarmallik.springemployeemanagement.service;

import org.springframework.data.domain.Page;

import com.sagarmallik.springemployeemanagement.model.Employee;
import com.sagarmallik.springemployeemanagement.model.EmployeeRequestDTO;
import com.sagarmallik.springemployeemanagement.repository.EmployeeRepository;

public interface EmployeeService {
  /**
   * Adds a new employee to the system with the provided details.
   *
   * This method creates a new employee record in the system using the information
   * provided in the EmployeeRequestDTO.
   *
   * @param employeeRequestDTO The EmployeeRequestDTO object containing the
   *                           details of the employee to be added.
   * @return The ID of the newly added employee
   * 
   * @see EmployeeRequestDTO
   * @see Employee
   * @see EmployeeRepository
   */
  String addEmployee(EmployeeRequestDTO employeeRequestDTO);

  /**
   * Retrieves a paginated list of employees from the system.
   *
   * This method fetches a subset of employees based on the provided page number,
   * page size, and sorting criteria.
   *
   * @param page   The page number to retrieve (starting from 0 for the first
   *               page).
   * @param size   The maximum number of employees to return per page.
   * @param sortBy The field by which the employee records will be sorted.
   *               Valid values for the sortBy parameter depend on the Employee
   *               entity's properties.
   *               For example, "name" can be used to sort employees by their
   *               names.
   *
   * @return A Page object containing the list of employees for the specified page
   *         and size, along with pagination information such as the total number
   *         of pages and total number of records.
   */
  Page<Employee> getAllEmployees(int page, int size, String sortBy);

  /**
   * Deletes an employee from the system.
   *
   * This method removes the employee with the specified employeeId from the
   * system permanently.
   *
   * @param employeeId The employeeId of the employee to be deleted.
   */
  void deleteEmployee(String employeeId);

  /**
   * Updates an existing employee in the system.
   *
   * This method updates the employee with the specified employeeId using the
   * information provided in the EmployeeRequestDTO.
   *
   * @param employeeId         The employeeId of the employee to be
   *                           updated.
   * @param employeeRequestDTO The EmployeeRequestDTO object containing the
   *                           updated details of the employee.
   */
  void updateEmployee(String employeeId, EmployeeRequestDTO employeeRequestDTO);

  /**
   * Retrieves an employee from the system by their unique identifier.
   *
   * This method fetches the employee with the specified employeeId from the
   * system and returns the corresponding Employee object.
   *
   * @param employeeId The employeeId of the employee to be retrieved.
   * @return The Employee object representing the employee with the provided
   *         employeeId,
   *         or null if the employee does not exist in the system.
   */
  Employee getEmployeeById(String employeeId);

  /**
   * Retrieves the Nth level manager of an employee in the organization hierarchy.
   *
   * This method fetches the Nth level manager of the employee with the specified
   * employeeId
   * based on the organization's hierarchical structure.
   *
   * @param employeeId The employeeId of the employee whose manager is to be
   *                   retrieved.
   * @param level      The specific level of the manager in the organization
   *                   hierarchy to be fetched.
   *                   The level should be a non-negative integer, where 0
   *                   represents the direct manager of the employee.
   *                   A level of 1 indicates the manager's manager, level 2
   *                   indicates the manager's manager's manager, and so on.
   *
   * @return The Employee object representing the Nth level manager of the
   *         specified employee,
   *         or null if there is no manager at the specified level or if the
   *         employee does not exist in the system.
   */
  Employee getNthLevelManager(String employeeId, int level);
}

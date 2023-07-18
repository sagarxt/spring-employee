package com.sagarmallik.springemployeemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sagarmallik.springemployeemanagement.model.Employee;
import com.sagarmallik.springemployeemanagement.model.EmployeeRequestDTO;
import com.sagarmallik.springemployeemanagement.repository.EmployeeRepository;
import com.sagarmallik.springemployeemanagement.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final JavaMailSender javaMailSender;

  @Override
  public String addEmployee(EmployeeRequestDTO employeeRequestDTO) {
    String employeeId = UUID.randomUUID().toString();
    var employee = new Employee();

    BeanUtils.copyProperties(employeeRequestDTO, employee);

    employee.setEmployeeId(employeeId);
    employeeRepository.insert(employee);

    if (employee.getReportsTo() != null) {
      Employee manager = employeeRepository.findById(employee.getReportsTo()).orElse(null);
      if (manager != null) {
        sendMailToManager(manager, employee);
      }
    }
    return employee.getEmployeeId();
  }
  /*
   * @Override
   * public List<Employee> getAllEmployees() {
   * return employeeRepository.findAll();
   * }
   */

  @Override
  public Page<Employee> getAllEmployees(int page, int size, String sortBy) {
    Sort sort = Sort.by(Direction.ASC, sortBy);

    PageRequest pageRequest = PageRequest.of(page, size, sort);

    Page<Employee> employees = employeeRepository.findAll(pageRequest);

    return employees;
  }

  @Override
  public void deleteEmployee(String employeeId) {
    employeeRepository.deleteById(employeeId);
  }

  @Override
  public void updateEmployee(String employeeId, EmployeeRequestDTO employeeRequestDTO) {
    var employee = getEmployeeById(employeeId);

    BeanUtils.copyProperties(employeeRequestDTO, employee);

    employeeRepository.save(employee);
  }

  @Override
  public Employee getEmployeeById(String employeeId) {
    return employeeRepository.findById(employeeId).orElse(null);
  }

  @Override
  public Employee getNthLevelManager(String employeeId, int level) {
    var employee = getEmployeeById(employeeId);
    if (employee == null) {
      return null;
    }
    Employee manager = getNthLevelManagerHelper(employee, level);
    return manager;
  }

  private Employee getNthLevelManagerHelper(Employee employee, int level) {
    if (employee == null || level <= 0) {
      return null;
    }

    if (level == 1) {
      String reportsToId = employee.getReportsTo();
      if (reportsToId != null) {
        return getEmployeeById(reportsToId);
      } else {
        return null;
      }
    } else {
      String reportsToId = employee.getReportsTo();
      if (reportsToId != null) {
        Employee manager = getEmployeeById(reportsToId);
        return getNthLevelManagerHelper(manager, level - 1);
      } else {
        return null;
      }
    }
  }

  private void sendMailToManager(Employee manager, Employee employee) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("testmailemployee@gmail.com");
    message.setTo(manager.getEmail());
    message.setSubject("New Employee Added");
    message.setText(
        employee.getEmployeeName() + " will now work under you. Mobile number is " +
            employee.getPhoneNumber() + " and email is " + employee.getEmail() + ".");
    javaMailSender.send(message);
    System.out.println("Email sent to " + manager.getEmail());
  }
}

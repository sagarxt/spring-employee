package com.sagarmallik.springemployeemanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sagarmallik.springemployeemanagement.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}

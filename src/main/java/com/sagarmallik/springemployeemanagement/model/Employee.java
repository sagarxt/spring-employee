package com.sagarmallik.springemployeemanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document
public class Employee {
  @Id
  private String employeeId;
  private String employeeName;
  private String phoneNumber;
  private String email;
  private String reportsTo;
  private String profileImage;
}

package com.sagarmallik.springemployeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeRequestDTO {
  private String employeeName;
  private String phoneNumber;
  private String email;
  private String reportsTo;
  private String profileImage;
}

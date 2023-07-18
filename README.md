# Spring Boot Employee Management App

This project is a Spring Boot-based Employee Management App that allows you to add, retrieve, update, and delete employees. The application uses a NoSQL database (MongoDB) to store employee information and provides RESTful API endpoints for interacting with the data.

## How to Run the Project

Prerequisites:
  - Java Development Kit (JDK) 11 or later installed.
  - Apache Maven installed.
  - MongoDB installed and running on localhost (or adjust the database configuration in `application.properties`).

2. Extract the project from the zip file.
3. Opet the project file in IDE and run the project by running SpringemployeemanagementApplication.java file.
  You find the file at 'src\main\java\com\sagarmallik\springemployeemanagement\'.
4. 
5. The application will start and be accessible at `http://localhost:2023`.

## Database Setup

The application uses MongoDB as the NoSQL database. Please ensure you have MongoDB installed and running on localhost.

## Basic API Documentation

### Add Employee
- Endpoint: POST /api/v1/employee
- Input JSON Structure:
  {
  "employeeName": "Sagar Mallik",
  "phoneNumber": "9998887776",
  "email": "1999sagarmallik@gmail.com",
  "reportsTo": "manager-employee-id",
  "profileImage": "https://example.com/profiles/sagar-mallik.jpg"
  }

### Get All Employees with Pagination and Sorting
- Endpoint: GET /api/v1/employee/0/10/employeeName
- Path Variable:
    page (required, Integer): Page number (0-indexed) for pagination.
    size (required, Integer): Number of employees per page.
    sortBy (required, String): Field to sort by (e.g., "employeeName", "email").

### Delete Employee by ID
- Endpoint: DELETE /api/v1/employees/{employeeId}
- Path Variable: employeeId (String): ID of the employee to delete.

### Update Employee by ID
- Endpoint: PUT /employees/{employeeId}
- Path Variable: employeeId (String): ID of the employee to update.
- Input JSON Structure:
    {
      "employeeName": "Updated Name",
      "phoneNumber": "9876543210",
      "email": "updated.email@example.com",
      "reportsTo": "updated-manager-employee-id",
      "profileImage": "https://example.com/profiles/updated.jpg"
    }

### Get nth Level Manager of an Employee
- Endpoint: GET /api/v1/employees/{employeeId}/manager/{level}
- Path Variables:
    employeeId (String): ID of the employee to find the manager.
    level (Integer): The desired level of the manager (e.g., 1, 2, 3)

I have completede all the Entry Level and Intermediate tasks. But I have completed only one task  Send Email to Level 1 Manager on New Employee Addition.

If any query/issue contact me @1999sagarmallik@gmail.com

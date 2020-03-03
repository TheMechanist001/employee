package com.example.employee.dao;

import com.example.employee.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeDao
{
    int insertEmployee(UUID id, Employee employee);

    default int insertEmployee(Employee employee)
    {
        UUID id = UUID.randomUUID();
        return insertEmployee(id, employee);
    }

    List<Employee> selectAllEmployee();

    Optional<Employee> selectEmployeeById(UUID id);

    int updateEmployeeById(UUID id, Employee employee);

    int deleteEmployeeById(UUID id);
}

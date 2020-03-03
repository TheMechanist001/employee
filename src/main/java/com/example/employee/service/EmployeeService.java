package com.example.employee.service;

import com.example.employee.dao.EmployeeDao;
import com.example.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService
{
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(@Qualifier("postgres") EmployeeDao employeeDao)
    {
        this.employeeDao = employeeDao;
    }

    public int addEmployee(Employee employee)
    {
        return employeeDao.insertEmployee(employee);
    }

    public List<Employee> getAllEmployee()
    {
        return employeeDao.selectAllEmployee();
    }

    public Optional<Employee> getEmployeeById(UUID id)
    {
        return employeeDao.selectEmployeeById(id);
    }

    public int updateEmployee(UUID id, Employee newEmployee)
    {
        return employeeDao.updateEmployeeById(id, newEmployee);
    }

    public int deleteEmployee(UUID id)
    {
        return employeeDao.deleteEmployeeById(id);
    }
}

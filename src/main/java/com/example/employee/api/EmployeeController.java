package com.example.employee.api;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/employee")
@RestController
public class EmployeeController
{
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void addEmployee(@Valid @NotNull @RequestBody Employee employee)
    {
        employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployee()
    {
        return employeeService.getAllEmployee();
    }

    @GetMapping(path = {"id"})
    public Employee getEmployeeById(@PathVariable("id") UUID id)
    {
        return employeeService.getEmployeeById(id).orElse(null);
    }

    @PutMapping(path = {"id"})
    public void updateEmployeeById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Employee employeeToUpdate)
    {
        employeeService.updateEmployee(id, employeeToUpdate);
    }

    @DeleteMapping(path = {"id"})
    public void deleteEmployeeById(@PathVariable("id") UUID id)
    {
        employeeService.deleteEmployee(id);
    }
}

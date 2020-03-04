package com.example.employee.dao;

import com.example.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class EmployeeDataAccessService implements EmployeeDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDataAccessService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertEmployee(UUID id, Employee employee)
    {
        final String sql = "INSERT INTO employee (id, name, department) VALUES (uuid_generate_v4(),?,?);";
        return jdbcTemplate.update(sql,employee.getName(), employee.getDepartment());
    }

    @Override
    public List<Employee> selectAllEmployee()
    {
        final String sql = "SELECT id, name, department FROM employee";
        List<Employee> employees = jdbcTemplate.query(sql, (resultSet, i) ->
        {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");
            return new Employee(id, name, department);
        });
        return employees;
    }

    @Override
    public Optional<Employee> selectEmployeeById(UUID id)
    {
        final String sql = "SELECT id, name, department FROM employee WHERE id = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) ->
    {
        UUID employeeId = UUID.fromString(resultSet.getString("id"));
        String name = resultSet.getString("name");
        String department = resultSet.getString("department");
        return new Employee(employeeId, name, department);
    });
        return Optional.ofNullable(employee);
    }

    @Override
    public int updateEmployeeById(UUID id, Employee employee)
    {
        final String sql = "UPDATE employee SET name = ?, department = ? WHERE id = ?";

        return jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), id);
    }

    @Override
    public int deleteEmployeeById(UUID id)
    {
        final String sql = "DELETE  FROM employee WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}

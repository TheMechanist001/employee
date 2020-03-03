package com.example.employee.dao;

import com.example.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Logic for fake database implementation. This is not tied to any database.
@Repository("fakeDao")
public class FakeEmployeeDataAccessService implements EmployeeDao
{

    @Override
    public int insertEmployee(UUID id, Employee employee)
    {
        DB.add(new Employee(id, employee.getName(), employee.getDepartment()));
        return 1;
    }

    @Override
    public List<Employee> selectAllEmployee()
    {
        return DB;
    }

    @Override
    public Optional<Employee> selectEmployeeById(UUID id)
    {
        return DB.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteEmployeeById(UUID id)
    {
        Optional<Employee> personMaybe = selectEmployeeById(id);
        if(personMaybe.isEmpty())
        {
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updateEmployeeById(UUID id, Employee update)
    {
        return selectEmployeeById(id)
                .map(employee -> {
                    int indexOfEmployeeToUpdate = DB.indexOf(employee);
                    if(indexOfEmployeeToUpdate >= 0)
                    {
                        DB.set(indexOfEmployeeToUpdate, new Employee(id, update.getName(), update.getDepartment()));
                        return 1;
                    }
                    return 0;
                } )
                .orElse(0);
    }
    //fake database
    private static List<Employee> DB = new ArrayList<>();
}

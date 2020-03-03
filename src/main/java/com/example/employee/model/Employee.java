package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Employee
{
    private final UUID id;
    @NotBlank
    private final String name;
    private final String department;


    public Employee(@JsonProperty("id") UUID id,
                    @JsonProperty("name") String name,
                    @JsonProperty("department") String department)
    {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public UUID getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDepartment()
    {
        return department;
    }
}

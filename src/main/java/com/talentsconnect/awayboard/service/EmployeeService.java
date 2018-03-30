package com.talentsconnect.awayboard.service;

import com.talentsconnect.awayboard.dto.EmployeeDto;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;

/**
 * Created by ritesh on 27/3/18.
 */

public interface EmployeeService {
    Employee saveEmployee(EmployeeDto employeeDto);
    Team postEmployeeToTeam(Long teamId, Long employeeId);
    Team deleteEmployeeFromTeam(Long teamId, Long employeeId);
    Employee updateEmployeeStatus(Long employeeId, Employee.Status status);
    Employee updateEmployee(EmployeeDto employeeDto);
}

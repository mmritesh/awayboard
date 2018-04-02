package com.talentsconnect.awayboard.service;

import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;

/**
 * Created by ritesh on 27/3/18.
 */

public interface AppService {
    Team putEmployeeToTeam(Long teamId, Long employeeId);
    Team deleteEmployeeFromTeam(Long teamId, Long employeeId);
    Employee updateEmployeeStatus(Long employeeId, Employee.Status status);
    void deleteEmployee(Long id);
    void deleteTeam(Long id);
    int removeTeamFromEmployee(Long empId, Long teamId);
}

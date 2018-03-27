package com.talentsconnect.awayboard.dto;

import com.talentsconnect.awayboard.entity.Employee;
import lombok.Data;

import java.util.List;

/**
 * Created by ritesh on 27/3/18.
 */

@Data
public class EmployeeDto {

    private Long id;

    private String name;

    private String imageUrl;

    private Employee.Status currentStatus;

    private List<Long> teams;
}

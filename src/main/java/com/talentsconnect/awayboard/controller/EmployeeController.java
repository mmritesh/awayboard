package com.talentsconnect.awayboard.controller;

import com.talentsconnect.awayboard.dto.EmployeeDto;
import com.talentsconnect.awayboard.dto.ServiceResponse;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ritesh on 27/3/18.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private EmployeeRepo employeeRepo;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo, EmployeeService employeeService) {
        this.employeeRepo = employeeRepo;
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<ServiceResponse<List<Employee>>> getAllEmployees(){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.findAll()));
    }

    @GetMapping("/employee/{emp-id}")
    public ResponseEntity<ServiceResponse<Employee>> getEmployee(@RequestParam("emp-id") Long id){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.findById(id)));
    }

    @PostMapping("/employee")
    public ResponseEntity<ServiceResponse<List<Employee>>> postEmployee(@RequestBody EmployeeDto employee){
        employeeService.saveEmployee(employee);
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.findAll()));
    }

    @PutMapping("/employee")
    public ResponseEntity<ServiceResponse<List<Employee>>> updateEmployee(@RequestBody EmployeeDto employeeDto){
        employeeService.updateEmployee(employeeDto);
        return ResponseEntity.ok().body(new ServiceResponse<List<Employee>>(employeeRepo.findAll()));
    }
    @PutMapping("/employee/{employee-id}/status/{status}")
    public ResponseEntity<ServiceResponse<Employee>> updateEmployeeStatus(@PathVariable("employee-id") Long employeeId,
                                                                          @PathVariable("status") Employee.Status status){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeService.updateEmployeeStatus(employeeId, status)));
    }

    @DeleteMapping("/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Void>> deleteMapping(@PathVariable("employee-id") Long id){
        employeeRepo.delete(id);
        return ResponseEntity.ok().body(new ServiceResponse<>(null));
    }
     /*@PutMapping("/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Employee>> updateEmployee(@RequestBody EmployeeDto employee){
        return null;
    }*/

}

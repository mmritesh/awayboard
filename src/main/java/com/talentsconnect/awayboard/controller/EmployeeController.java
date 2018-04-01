package com.talentsconnect.awayboard.controller;

import com.talentsconnect.awayboard.dto.ServiceResponse;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.service.AppService;
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
    private AppService appService;

    @Autowired
    public EmployeeController(EmployeeRepo employeeRepo, AppService appService) {
        this.employeeRepo = employeeRepo;
        this.appService = appService;
    }

    @GetMapping("/employees")
    public ResponseEntity<ServiceResponse<List<Employee>>> getAllEmployees(){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.findAll()));
    }

    @GetMapping("/employee/{emp-id}")
    public ResponseEntity<ServiceResponse<Employee>> getEmployee(@PathVariable("emp-id") Long id){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.findById(id)));
    }

    @PostMapping("/employee")
    public ResponseEntity<ServiceResponse<Employee>> postEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.save(employee)));
    }

    @PutMapping("/employee")
    public ResponseEntity<ServiceResponse<Employee>> updateEmployee(@RequestBody Employee employee){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeRepo.save(employee)));
    }
    @PutMapping("/employee/{employee-id}/status/{status}")
    public ResponseEntity<ServiceResponse<Employee>> updateEmployeeStatus(@PathVariable("employee-id") Long employeeId,
                                                                          @PathVariable("status") Employee.Status status){
        return ResponseEntity.ok().body(new ServiceResponse<>(appService.updateEmployeeStatus(employeeId, status)));
    }

    @DeleteMapping("/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Void>> deleteEmployee(@PathVariable("employee-id") Long id){
        appService.deleteEmployee(id);
        return ResponseEntity.ok().body(new ServiceResponse<>(null));
    }

}

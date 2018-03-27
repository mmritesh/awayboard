package com.talentsconnect.awayboard.repo;

import com.talentsconnect.awayboard.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ritesh on 26/3/18.
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    Employee findById(Long id);
}

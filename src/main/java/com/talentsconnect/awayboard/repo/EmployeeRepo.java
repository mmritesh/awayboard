package com.talentsconnect.awayboard.repo;

import com.talentsconnect.awayboard.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ritesh on 26/3/18.
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
    Employee findById(Long id);

    @Modifying
    @Query(value = "delete from emp_team where emp_id = ?1", nativeQuery = true)
    int deleteEmployeeTeamRelation(Long id);
}

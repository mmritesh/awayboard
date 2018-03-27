package com.talentsconnect.awayboard.repo;

import com.talentsconnect.awayboard.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ritesh on 26/3/18.
 */
@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {
    Team findById(Long id);
}

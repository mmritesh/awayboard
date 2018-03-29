package com.talentsconnect.awayboard.service;

import com.talentsconnect.awayboard.dto.TeamDto;
import com.talentsconnect.awayboard.entity.Team;

/**
 * Created by ritesh on 29/3/18.
 */
public interface TeamService {
    Team updateTeam(TeamDto team);
    Team saveTeam(TeamDto teamDto);
}

package com.talentsconnect.awayboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by ritesh on 29/3/18.
 */

@Getter
@Setter
public class TeamDto {
    private Long id;
    private String name;
    private String imageUrl;
    private List<Long> employees;
}

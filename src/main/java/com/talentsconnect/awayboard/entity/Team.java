package com.talentsconnect.awayboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * Created by ritesh on 26/3/18.
 */

@Entity
@Getter
@Setter
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @ManyToMany(cascade = {
            CascadeType.REMOVE
    })
    @JoinTable(
            name="EMP_TEAM",
            inverseJoinColumns={ @JoinColumn(name="EMP_ID", referencedColumnName="id") },
            joinColumns={ @JoinColumn(name="TEAM_ID", referencedColumnName="id") }
    )
    @JsonIgnoreProperties("teams")
    private Set<Employee> employees;
}

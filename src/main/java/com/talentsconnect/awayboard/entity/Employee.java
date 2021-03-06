package com.talentsconnect.awayboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by ritesh on 26/3/18.
 */

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

    public enum Status{
        OFFICE("Office"),
        HOME_OFFICE("Home Office"),
        AWAY("Away");

        private String name;
        Status(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageUrl;

    @Column
    private Status currentStatus;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnoreProperties("employees")
    private Set<Team> teams;

}

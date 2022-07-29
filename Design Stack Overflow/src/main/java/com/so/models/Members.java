package com.so.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table
@NoArgsConstructor
public class Members extends BaseEntity{

    private String name;
    private String emailId;
    @Column(unique = true)
    private String userId;
    //private String password;
    private Double points = 0D;
}

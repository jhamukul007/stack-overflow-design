package com.so.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Badges extends BaseEntity{
    private String title;
    private int ranking;
    private double startPoint;
    private double endPoint;
}

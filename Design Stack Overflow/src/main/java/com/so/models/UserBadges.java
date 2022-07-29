package com.so.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table
@NoArgsConstructor
public class UserBadges extends BaseEntity{

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Badges.class)
    private Badges badges;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Members.class)
    private Members member;

}

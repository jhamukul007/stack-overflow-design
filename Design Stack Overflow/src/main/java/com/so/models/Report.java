package com.so.models;

import com.so.enums.EntityType;
import com.so.enums.ReportType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table
@NoArgsConstructor
public class Report extends BaseEntity {

    private Long entityId;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @Column(name = "report_type")
    @Enumerated(EnumType.STRING)
    private ReportType ReportType;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Members.class)
    private Members member;
}

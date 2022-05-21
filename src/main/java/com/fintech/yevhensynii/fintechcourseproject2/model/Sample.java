package com.fintech.yevhensynii.fintechcourseproject2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "samples")
@Setter
@Getter
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sampleId;

    @Column(name = "sample_name")
    private String sampleName;

    @Column
    private String iban;

    @Column
    private String okpo;

    @Column
    private String appointment;

    @Column(name = "address_id")
    private long addressId;
}

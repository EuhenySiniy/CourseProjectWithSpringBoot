package com.fintech.yevhensynii.fintechcourseproject2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
@Setter
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @Column(name = "card_number")
    private String cardNum;

    @Column(name = "sum_payment")
    private BigDecimal sum;

    @Column
    private String status;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "date_status")
    private Timestamp dateStatus;

    @Column(name = "sample_id")
    private long sampleId;
}

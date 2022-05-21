package com.fintech.yevhensynii.fintechcourseproject2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Setter
@Getter
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "house_num")
    private String houseNum;

    @Column(name = "apartment_num")
    private String apartmentNum;

    @Column(name = "user_id")
    private long userId;
}

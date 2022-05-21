package com.fintech.yevhensynii.fintechcourseproject2.repositories;

import com.fintech.yevhensynii.fintechcourseproject2.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    UserAddress getUserAddressByCityAndStreetAndHouseNumAndApartmentNumAndUserId(String city, String street, String houseNum, String apartmentNum, Long userId);
}

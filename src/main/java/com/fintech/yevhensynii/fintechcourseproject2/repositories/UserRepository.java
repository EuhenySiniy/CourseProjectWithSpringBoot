package com.fintech.yevhensynii.fintechcourseproject2.repositories;

import com.fintech.yevhensynii.fintechcourseproject2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}

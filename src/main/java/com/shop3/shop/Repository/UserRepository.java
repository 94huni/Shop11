package com.shop3.shop.Repository;

import com.shop3.shop.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(String username);

    Page<User> findAll(Pageable pageable);
}

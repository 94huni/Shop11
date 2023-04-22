package com.shop3.shop.Repository;

import com.shop3.shop.DTO.UserDto;
import com.shop3.shop.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Objects;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserid(String userid);
    @Query("SELECT new com.shop3.shop.DTO.UserDto(u.userid, u.username, u.email, u.address, u.phone) FROM User u WHERE u.userid = :userid")
    UserDto getByUserid(@Param("userid") String userid);

    //SELECT * FROM name LIKE CONCAT(`%', #{searchKeyword}, '%') ORDER BY id ASC LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}
    Page<User> findUserByUseridContaining(String searchKeyword, Pageable pageable);

    @Query("SELECT new com.shop3.shop.DTO.UserDto(u.userid, u.username, u.email, u.address, u.phone) FROM User u WHERE u.id = :id")
    UserDto getUserById(@Param("id") Long id);
}

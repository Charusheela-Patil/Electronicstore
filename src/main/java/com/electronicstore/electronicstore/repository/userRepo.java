package com.electronicstore.electronicstore.repository;

import com.electronicstore.electronicstore.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import com.electronicstore.User.UserDto;

import java.util.List;

@Repository
public interface userRepo extends JpaRepository<User, String > {


        public User findByEmail (String email);

        public List<User> findByUseridContaining (String pattern);
}

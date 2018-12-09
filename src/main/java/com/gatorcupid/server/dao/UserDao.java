package com.gatorcupid.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gatorcupid.server.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Long>{

	@Query("select u from User u where u.email = :email AND u.status != 1")
	User findByEmail(@Param("email") String email);
}

package com.gatorcupid.server.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gatorcupid.server.constants.Gender;
import com.gatorcupid.server.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Long>{

	@Query("select u from User u where u.email = :email AND u.status = 1")
	User findUnregisteredByEmail(@Param("email") String email);
	
	@Query("select u from User u where u.id = :id AND u.status = 1")
	User findUserById(@Param("id") Long id);
	
	@Query("select u from User u where u.id !=:userId AND u.gender=:interestedIn AND u.status = 1 AND u.isProfileCreated = 1")
	Page<User> findBrowsingListByUserId(@Param("userId") Long userId, @Param("interestedIn")Gender interestedIn, Pageable pageable);
}

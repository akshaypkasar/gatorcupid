package com.gatorcupid.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gatorcupid.server.model.User;
import com.gatorcupid.server.model.UserSession;

@Repository
public interface UserSessionDao extends JpaRepository<UserSession, Long> {
	
	@Query("select us from UserSession us where us.user.id=:userId AND us.status = 1")
	UserSession findActiveSessionByUserId(@Param("userId")Long userId);
	
	@Query("select us from UserSession us where us.user=:user AND us.status = 1")
	UserSession findActiveSessionByUser(@Param("user")User user);

}

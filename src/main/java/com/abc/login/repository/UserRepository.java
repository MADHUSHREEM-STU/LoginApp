package com.abc.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abc.login.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	
	public UserEntity findByEmail(String email);
	
	@Query("Select u from UserEntity u where u.email = :uemail")
	public List<UserEntity> findUsersByEmail(@Param("uemail")String email);

}

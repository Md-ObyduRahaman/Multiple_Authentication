package com.multiple.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multiple.authentication.model.UserSecurityKey;

public interface UserSecretKeyRepo extends JpaRepository<UserSecurityKey, Integer> {

}

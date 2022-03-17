package com.alexnail.hertzcch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexnail.hertzcch.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

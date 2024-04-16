package com.pfe_app.eya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.entities.project;

@Repository
public interface ProjectRepository extends JpaRepository<project, Long> {

	Optional<project> findAllByUser(User user);


}

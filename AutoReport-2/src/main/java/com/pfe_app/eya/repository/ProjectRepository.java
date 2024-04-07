package com.pfe_app.eya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe_app.eya.entities.project;

@Repository
public interface ProjectRepository extends JpaRepository<project, Long> {

}

package com.pfe_app.eya.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe_app.eya.dto.EmployeeDto;
import com.pfe_app.eya.entities.User;
import com.pfe_app.eya.enums.UserRole;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByRole(UserRole userRole);

	Optional<User> findFirstByEmail(String email);

	List<User> findAllByRole(UserRole userRole);

}

package com.pfe_app.eya.service.admin;

import java.util.Optional;

import com.pfe_app.eya.entities.User;

public interface AdminService {

	Optional<User> getUser(String email, String password);
}

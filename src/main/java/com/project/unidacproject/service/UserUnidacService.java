package com.project.unidacproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.unidacproject.model.UserUnidac;
import com.project.unidacproject.repository.UserUnidacRepository;

@Service
public class UserUnidacService {

	private UserUnidacRepository userUnidacRepository;

	public UserUnidacService(UserUnidacRepository userUnidacRepository) {
		super();
		this.userUnidacRepository = userUnidacRepository;
	}

	public List<UserUnidac> findAllUserUnidac(String name) {
		if (name == null) {
			return userUnidacRepository.findAll();
		} else {
			return userUnidacRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public UserUnidac findUserUnidacById(Long id) {

		return userUnidacRepository.findById(id).orElse(null);

	}

	public UserUnidac saveUserUnidac(UserUnidac userUnidac) {
		userUnidac.setId(null);
		return userUnidacRepository.save(userUnidac);
	}

	public UserUnidac updateUserUnidac(UserUnidac userUnidac) {
		return userUnidacRepository.save(userUnidac);
	}

	public void deleteUserUnidacById(Long id) {
		userUnidacRepository.deleteById(id);
	}

	public void deleteAllUserUnidac() {
		userUnidacRepository.deleteAll();
	}

}

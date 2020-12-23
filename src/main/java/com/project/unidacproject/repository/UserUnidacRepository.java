package com.project.unidacproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.unidacproject.model.UserUnidac;

@Repository
public interface UserUnidacRepository extends JpaRepository<UserUnidac, Long> {

	List<UserUnidac> findByNameContainingIgnoreCase(String name);

}

package com.sg.billback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sg.billback.model.AppRole;
import com.sg.billback.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

	void deleteByRoleName(AppRole dbRole);
}

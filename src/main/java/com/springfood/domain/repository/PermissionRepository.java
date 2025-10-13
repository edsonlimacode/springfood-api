package com.springfood.domain.repository;

import com.springfood.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}

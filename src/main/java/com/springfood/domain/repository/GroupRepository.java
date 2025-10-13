package com.springfood.domain.repository;

import com.springfood.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> { }

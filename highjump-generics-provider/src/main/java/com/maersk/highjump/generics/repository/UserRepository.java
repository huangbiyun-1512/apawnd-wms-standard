package com.maersk.highjump.generics.repository;

import com.maersk.highjump.generics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

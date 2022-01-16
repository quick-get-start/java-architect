package com.start.quick.repository;

import com.start.quick.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String> {

    boolean existsByUsername(String username);
}

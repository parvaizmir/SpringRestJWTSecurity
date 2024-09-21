package com.rest.Rest.Repo;

import com.rest.Rest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
  User  findByUsername(String username);
  boolean deleteByUsername(String username);
}

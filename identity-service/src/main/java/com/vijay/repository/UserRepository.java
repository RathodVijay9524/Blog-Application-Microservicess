package com.vijay.repository;

import java.util.List;
import java.util.Optional;

import com.vijay.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
   
    Optional<User> findByEmailAndPassword(String email,String password);

    List<User> findByNameContaining(String keywords);

    Optional<User> findByName(String username);

}

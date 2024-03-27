package com.vijay.repository;

import java.util.Optional;

import com.vijay.entites.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long>{

	
	Optional<Worker> findByEmail(String email);

    Optional<Worker> findByUsernameOrEmail(String username, String email);

    Optional<Worker> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

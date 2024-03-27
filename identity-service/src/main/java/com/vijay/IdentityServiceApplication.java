package com.vijay;

import com.vijay.entites.Role;
import com.vijay.entites.User;
import com.vijay.entites.Worker;
import com.vijay.repository.UserRepository;
import com.vijay.repository.WorkerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableFeignClients
public class IdentityServiceApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WorkerRepository workerRepository;



	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

	@PostConstruct
	protected void init() {

		if (userRepository.count() == 0 && workerRepository.count() == 0) {

			Set<Role> adminRoles = new HashSet<>();
			adminRoles.add(createRole("ROLE_ADMIN"));
			//adminRoles.add(createRole("ROLE_SUPER_USER"));
			// adminRoles.add(createRole("ROLE_NORMAL"));
			//adminRoles.add(createRole("ROLE_WORKER"));


			Set<Role> userRoles = new HashSet<>();
			userRoles.add(createRole("ROLE_SUPER_USER"));

			Set<Role> normalRoles = new HashSet<>();
			normalRoles.add(createRole("ROLE_NORMAL"));

			Set<Role> workerRoles = new HashSet<>();
			workerRoles.add(createRole("ROLE_WORKER"));




			// Create users
			User admin = User.builder()
					.name("Vimal Kumar")
					.email("admin@gmail.com")
					.username("admin")
					.password(passwordEncoder.encode("admin"))
					.roles(adminRoles)
					.build();

			User user = User.builder()
					.name("Ajay Rawat")
					.email("user@gmail.com")
					.username("user")
					.password(passwordEncoder.encode("user"))
					.roles(userRoles)
					.build();


			User normalUser = User.builder()
					.name("Vijay Rathod")
					.email("normal@gmail.com")
					.username("normal")
					.password(passwordEncoder.encode("normal"))
					.roles(normalRoles)
					.build();



			// Save users
			 userRepository.saveAll(Arrays.asList(admin, user, normalUser));

			Worker worker=Worker.builder()
					.name("Salman Khan")
					.email("worker@gmail.com")
					.username("worker")
					.password(passwordEncoder.encode("worker"))
					.roles(workerRoles)
					.user(admin)
					.build();

			workerRepository.save(worker);

		}


	}

	private Role createRole(String roleName) {
		Role role = new Role();
		role.setName(roleName);
		return role;
	}


}




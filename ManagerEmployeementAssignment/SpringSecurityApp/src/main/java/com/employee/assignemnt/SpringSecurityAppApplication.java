package com.employee.assignemnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.employee.assignemnt.dao.ManagerDao;
import com.employee.assignemnt.model.Manager;

@SpringBootApplication
public class SpringSecurityAppApplication {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ManagerDao managerDao) {
		return args -> {
			Manager manager1 = new Manager();
			manager1.setEmail("abc@gmail.com");
			manager1.setPassword(passwordEncoder.encode("chinmay"));
			manager1.setFirstname("Abc");
			manager1.setLastname("xyz");
			manager1.setAddress("Pune");
			manager1.setCompany("Company Name");
			manager1.setDob("26/10/1985");
			managerDao.save(manager1);

			Manager manager2 = new Manager();
			manager2.setEmail("Chinmay@gmail.com");
			manager2.setPassword(passwordEncoder.encode("chinmay"));
			manager2.setFirstname("chinmay");
			manager2.setLastname("p");
			manager2.setAddress("Pune");
			manager2.setCompany("Company Name");
			manager2.setDob("26/10/1985");
			managerDao.save(manager2);

		};
	}

}

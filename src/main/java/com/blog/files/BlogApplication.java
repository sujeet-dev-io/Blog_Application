package com.blog.files;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableJpaRepositories
public class BlogApplication implements CommandLineRunner {

	private final ApplicationContext applicationContext;

	public BlogApplication(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Hello World");

//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String encodedPassword = passwordEncoder.encode("P@ssw0rd123!");
//		System.out.println(encodedPassword);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
		System.out.println(passwordEncoder.encode("P@ssw0rd123!"));
	}
}

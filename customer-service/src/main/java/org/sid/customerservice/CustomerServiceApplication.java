package org.sid.customerservice;

import org.sid.customerservice.Entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository) {
		return args -> {
					customerRepository.save(Customer.builder().
							name("said").email("said@gmail.com").build());
					customerRepository.save(Customer.builder().
							name("amal").email("amal@gmail.com").build());
					customerRepository.save(Customer.builder().
							name("houda").email("houda@gmail.com").build());
					customerRepository.findAll().forEach(c -> {
						System.out.println("------------");
						System.out.println(c.getId());
						System.out.println(c.getName());
						System.out.println(c.getEmail());
						System.out.println("------------");
					});
			};
	}
}

package com.springboot.jpa.mapping;

import com.springboot.jpa.mapping.dao.AppDAO;
import com.springboot.jpa.mapping.entity.Instructor;
import com.springboot.jpa.mapping.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner -> {
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("Erdogan","Akpolat","erdogan@akpolat.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.erdogan.com","Coding ");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);


		//save the instructor
		//
		//NOTE: this will also save the details object
		// because of CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}
}

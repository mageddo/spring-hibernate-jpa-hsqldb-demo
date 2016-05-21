package com.geowarin.hibernate.jpa.standalone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.geowarin.hibernate.jpa.standalone.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple standalone JPA app.
 * Will load the user inserted by the script import-users.sql
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
@SpringBootApplication
public class App {
	
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}
	
	public static void main2(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		User found = entityManager.find(User.class, 1L);
		log.info("found=" + found);
	}
}

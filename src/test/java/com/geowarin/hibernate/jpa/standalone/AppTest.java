package com.geowarin.hibernate.jpa.standalone;

import java.util.List;

import javax.persistence.EntityManager;

import com.geowarin.hibernate.jpa.standalone.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
@CustomContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

	@Autowired
	EntityManager entityManager;

	@Test
	public void testFind() {
		
		User user = entityManager.find(User.class, 1L);
		Assert.assertNotNull(user);
		Assert.assertEquals("userTest", user.getName());
	}
	
	@Test
	public void testInsert() {
		
		User newUser = new User();
		newUser.setName("insert");
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(newUser);
		long id = newUser.getId();
		
		entityManager.getTransaction().commit();
		
		User user = entityManager.find(User.class, id);
		Assert.assertNotNull(user);
		Assert.assertEquals("insert", user.getName());
	}
	
	@Test
	public void testFindAll() {
		
		List<User> allUsers = entityManager.createQuery("from User").getResultList();
		Assert.assertEquals(2, allUsers.size());
	}

}

package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Transactional
class ReproductionHibernateTest {

	@Autowired
	EntityManager em;

	private UUID setup() {
		final Person person = new Person("Some person");
		em.persist(person);
		for (int i = 0; i < 5; i++) {
			final PrivateData privateData = new PrivateData(person, "Some data " + i);
			em.persist(privateData);
			for (int j = 0; j < 5; j++) {
				em.persist(new PrivateSubData(privateData, "Some subdata " + i));
			}
		}
		em.flush();
		return person.getId();
	}

	@Test
	void testAnonymize() {
		UUID personId = setup();

		final Query query = em.createQuery("UPDATE PrivateSubData psd set psd.dataName = '...' where psd.data.person.id = ?1");
		query.setParameter(1, personId);
		query.executeUpdate();
	}

	@Test
	void testDelete() {
		UUID personId = setup();

		final Query query = em.createQuery("DELETE FROM PrivateSubData psd where psd.data.person.id = ?1");
		query.setParameter(1, personId);
		query.executeUpdate();
	}
}

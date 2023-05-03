package com.example.demo;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Transactional
class ReproductionSpringDataTest {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PrivateDataRepository privateDataRepository;

	@Autowired
	private PrivateSubDataRepository privateSubDataRepository;

	private UUID setup() {
		Person person = personRepository.saveAndFlush(new Person("Some person"));
		for (int i = 0; i < 5; i++) {
			final PrivateData privateData = privateDataRepository.saveAndFlush(new PrivateData(person, "Some data " + i));
			for (int j = 0; j < 5; j++) {
				privateSubDataRepository.saveAndFlush(new PrivateSubData(privateData, "Some subdata " + i));
			}
		}
		return person.getId();
	}

	@Test
	void testAnonymize() {
		UUID personId = setup();
		privateDataRepository.anonymize(personId);
		privateSubDataRepository.anonymize(personId);
	}

	@Test
	void testDelete() {
		UUID personId = setup();
		privateSubDataRepository.deleteByPerson(personId);
	}
}

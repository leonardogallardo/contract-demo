package com.demo.contract.greeting

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [GreetingApplication::class])
abstract class GreetBase {

	@Autowired
	lateinit var wac: WebApplicationContext

	//	@MockBean PersonService personService;

	@Before
	fun setup() {
		RestAssuredMockMvc.webAppContextSetup(wac)
		//
		//		Mockito.when(personService.findPersonById(1L))
		//				.thenReturn(new Person(1L, "foo", "bee"));
	}

}

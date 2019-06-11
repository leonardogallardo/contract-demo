package com.demo.contract.coolgreeting

import org.assertj.core.api.BDDAssertions
import org.junit.Test
import org.springframework.web.client.RestTemplate
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.web.client.ResponseErrorHandler


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureStubRunner(ids = ["com.demo.contract:greeting:+:stubs:8100"], stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class ContractGreetingClientTest {

    @Test
    fun should_return_greeting_message() {
        // given:
        val restTemplate = RestTemplate()

        // and:
        val person = PersonGreeting("any", 2)

        // when:
        val personResponseEntity = restTemplate.postForEntity("http://localhost:8100/greeting", person, Message::class.java)

        // then:
        BDDAssertions.then(personResponseEntity.statusCodeValue).isEqualTo(200)
        BDDAssertions.then(personResponseEntity.body!!.message).isNotBlank()
    }

    @Test
    fun should_return_bad_request() {
        // given:
        val restTemplate = RestTemplate()
        restTemplate.errorHandler = ResponseErrorHandler {

        }

        // and:
        val person = PersonGreeting("", 7)

        // when:
        val personResponseEntity = restTemplate.postForEntity("http://localhost:8100/greeting", person, ApiError::class.java)

        // then:
        BDDAssertions.then(personResponseEntity.statusCodeValue).isEqualTo(400)
        BDDAssertions.then(personResponseEntity.statusCodeValue).isEqualTo("BAD_REQUEST")
        BDDAssertions.then(personResponseEntity.body!!.message).isNotBlank()
    }
}
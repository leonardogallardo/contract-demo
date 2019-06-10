package com.demo.contract.coolgreeting

import org.assertj.core.api.BDDAssertions
import org.junit.Test
import org.springframework.web.client.RestTemplate
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import java.util.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureStubRunner(ids = ["com.demo.contract:greeting:+:stubs:8100"], stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class ContractGreetingClientTest {

//    @get:Rule
//    var stubRunnerRule: StubRunnerRule = StubRunnerRule()
//            .downloadStub("com.demo.contract", "greeting", "0.0.1-SNAPSHOT", "stubs")
//            .withPort(8100)
//            .stubsMode(StubRunnerProperties.StubsMode.LOCAL)

    @Test
    fun should_return_greeting_message() {
        // given:
        val restTemplate = RestTemplate()

        // and:
        val person = PersonGreeting("any", Random().nextInt(3 + 1))

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

        // and:
        val person = PersonGreeting("", 4)

        // when:
        val personResponseEntity = restTemplate.postForEntity("http://localhost:8100/greeting", person, Message::class.java)

        // then:
        BDDAssertions.then(personResponseEntity.statusCodeValue).isEqualTo(400)
        BDDAssertions.then(personResponseEntity.body!!.message).isNotBlank()
    }
}
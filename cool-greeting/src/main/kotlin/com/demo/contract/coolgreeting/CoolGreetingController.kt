package com.demo.contract.coolgreeting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.validation.Valid

@RestController("/coolgreeting")
class CoolGreetingController {

    @Autowired
    lateinit var greetingClient: GreetingClient

    @PostMapping
    fun getCoolGreeting(@Valid @RequestBody person: Person): ResponseEntity<Any> {

        val category = getCategory(person)

        val pGreeting = PersonGreeting(person.name, category)
        val greeting = greetingClient.getGreeting(pGreeting)

        val message = Message(greeting.message + " Tem traquinas no armário e refri na geladeira :)")

        return ResponseEntity.ok(message)
    }

    private fun getCategory(person: Person): Int {
        if (person.birthDate == null) {
            return 3
        }

        val diffInYears = person.birthDate.until(LocalDate.now(), ChronoUnit.YEARS)

        return when {
            diffInYears <= 5 -> 1
            diffInYears <= 15 -> 2
            else -> 3
        }
    }


}
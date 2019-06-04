package com.demo.contract.greeting

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController("/greeting")
class GreetingController {


    @PostMapping
    fun getGreeting(@Valid @RequestBody person : Person) : ResponseEntity<Any> {

        val greet = when {
            person.category == 1 -> "Oizinho"
            person.category == 2 -> "Oie"
            person.category == 3 -> "Olá"
            else -> throw CategoryNotFoundException(person.category!!)
        }

        return ResponseEntity.ok(Message("$greet ${person.name}. Seja bem vindo!"))
    }

}
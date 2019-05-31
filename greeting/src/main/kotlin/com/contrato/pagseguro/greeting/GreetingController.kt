package com.contrato.pagseguro.greeting

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController("/greeting")
class GreetingController {


    @PostMapping
    fun getGreeting(@Valid @RequestBody person : Person) = Message("Olá ${person.name}. Seja bem vindo!")

}
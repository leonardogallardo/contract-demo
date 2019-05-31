package com.example.coolgreeting

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController("/coolgreeting")
class CoolGreetingController {

    @Autowired
    lateinit var greetingClient : GreetingClient

    @PostMapping
    fun getCoolGreeting(@Valid @RequestBody person: Person) = Message(greetingClient.getGreeting(person).message + " Tem traquinas no armário e refri na geladeira :)")

}
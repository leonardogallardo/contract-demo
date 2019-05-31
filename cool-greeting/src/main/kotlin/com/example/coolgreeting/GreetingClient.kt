package com.example.coolgreeting

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name="greeting", url = "http://localhost:8080", configuration = [GreetingConfiguration::class])
interface GreetingClient {

    @PostMapping(value = "/greeting")
    fun getGreeting(person : Person): Message

}
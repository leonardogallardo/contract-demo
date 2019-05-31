package com.demo.contract.coolgreeting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CoolGreetingApplication

fun main(args: Array<String>) {
	runApplication<CoolGreetingApplication>(*args)
}

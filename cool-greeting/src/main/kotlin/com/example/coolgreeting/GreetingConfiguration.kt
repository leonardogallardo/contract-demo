package com.example.coolgreeting

import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import feign.slf4j.Slf4jLogger
import org.springframework.beans.factory.annotation.Value


@Configuration
class GreetingConfiguration {

    @Bean
    fun feignLoggerLevel(@Value("\${feign.client.config.default.loggerLevel: NONE}") debugLevel: String): Logger.Level {
        return Logger.Level.valueOf(debugLevel)
    }

    @Bean
    fun logger(): Logger {
        return Slf4jLogger(GreetingClient::class.java)
    }

}

package com.demo.contract.coolgreeting

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Past

data class Person(

        @field:NotEmpty
        val name : String,

        @field:Past
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        val birthDate : LocalDate?

)
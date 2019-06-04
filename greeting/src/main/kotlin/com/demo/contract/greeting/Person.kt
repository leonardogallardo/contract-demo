package com.demo.contract.greeting

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class Person(

        @field:NotEmpty
        val name : String,

        @field:Min(value = 1)
        @field:Max(value = 3)
        @field:NotNull
        val category : Int
)
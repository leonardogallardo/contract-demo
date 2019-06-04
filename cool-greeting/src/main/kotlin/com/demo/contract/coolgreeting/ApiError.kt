package com.demo.contract.coolgreeting

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiError(
		val status: HttpStatus,
		val message: String,
		val errors: List<String>?
)

package com.demo.contract.coolgreeting

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.HttpStatus
import java.util.ArrayList
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.ConstraintViolationException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

	override fun handleMethodArgumentNotValid(
			ex: MethodArgumentNotValidException,
			headers: HttpHeaders,
			status: HttpStatus,
			request: WebRequest): ResponseEntity<Any> {

		val errors = ArrayList<String>()
		for (error in ex.bindingResult.fieldErrors) {
			errors.add(error.field + ": " + error.defaultMessage)
		}
		for (error in ex.bindingResult.globalErrors) {
			errors.add(error.objectName + ": " + error.defaultMessage)
		}

		val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, errors)
		return handleExceptionInternal(
				ex, apiError, headers, apiError.status, request)
	}

	override fun handleMissingServletRequestParameter(
			ex: MissingServletRequestParameterException, headers: HttpHeaders,
			status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
		val error = ex.parameterName + " parameter is missing"

		val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, arrayListOf(error))
		return ResponseEntity(
				apiError, HttpHeaders(), apiError.status)
	}

	override fun handleHttpMessageNotReadable(
			ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest) : ResponseEntity<Any> {

		val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, null)
		return ResponseEntity(
				apiError, HttpHeaders(), apiError.status)
	}

	@ExceptionHandler(ConstraintViolationException::class)
	fun handleConstraintViolation(
			ex: ConstraintViolationException, request: WebRequest): ResponseEntity<Any> {
		val errors = ArrayList<String>()
		for (violation in ex.constraintViolations) {
			errors.add(violation.rootBeanClass.name + " " +
					violation.propertyPath + ": " + violation.message)
		}

		val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, errors)
		return ResponseEntity(
				apiError, HttpHeaders(), apiError.status)
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException::class)
	fun handleMethodArgumentTypeMismatch(
			ex: MethodArgumentTypeMismatchException, request: WebRequest): ResponseEntity<Any> {
		val error = ex.name + " should be of type " + ex.requiredType!!.name

		val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage, arrayListOf(error))
		return ResponseEntity(
				apiError, HttpHeaders(), apiError.status)
	}

}

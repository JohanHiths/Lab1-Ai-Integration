package ai.ai.controller

import ai.ai.dto.ErrorResponse
import ai.ai.exception.InsufficientCreditsException
import ai.ai.exception.InvalidRequestException
import ai.ai.exception.ResourceNotFoundException
import ai.ai.exception.ServiceUnavailableException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ControllerAdvice
{


    @ExceptionHandler(InvalidRequestException::class)
    fun handleInvalidRequest(ex: InvalidRequestException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message ?: "Invalid request", 400),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(ServiceUnavailableException::class)
    fun handleServiceUnavailable(ex: ServiceUnavailableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(ex.message ?: "Service unavailable", 503),
            HttpStatus.SERVICE_UNAVAILABLE
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("Something went wrong", 500),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

}
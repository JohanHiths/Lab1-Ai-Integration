package ai.ai.controller

import ai.ai.dto.ErrorResponse
import ai.ai.InsufficientCreditsException
import ai.ai.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ControllerAdvice
{


    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            message = ex.message ?: "Invalid request",
            status = HttpStatus.BAD_REQUEST.value(),
            timestamp = java.time.Instant.now().toString()

        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }


    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            message = "Something went wrong",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            timestamp = java.time.Instant.now().toString()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
    }


    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleTodoNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            message = ex.message ?: "Fallback message",
            status = HttpStatus.NOT_FOUND.value(),
            timestamp = java.time.Instant.now().toString()
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(InsufficientCreditsException::class)
    fun handleInsufficientCredits(ex: InsufficientCreditsException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ex.message)
    }
}
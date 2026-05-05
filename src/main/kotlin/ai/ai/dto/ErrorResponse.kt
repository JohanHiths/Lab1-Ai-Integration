package ai.ai.dto

import java.time.Instant


data class ErrorResponse(
    val message: String,
    val status: Int,
    val timestamp: String = Instant.now().toString()


)

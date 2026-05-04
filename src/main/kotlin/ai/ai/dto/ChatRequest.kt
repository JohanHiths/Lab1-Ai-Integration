package ai.ai.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class ChatRequest(

    @field:Schema(example = "coder")
    @field:NotBlank(message = "personality must not be blank")
    val personality: String,

    @field:Schema(example = "How do I write a for-loop?")
    @field:NotBlank(message = "message must not be blank")
    val message: String,

    @field:Schema(example = "user-123")
    val sessionId: String?
)

package ai.ai.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ChatRequest(

    @field:Schema(example = "coder")
    val personality: String,

    @field:Schema(example = "How do I write a for-loop?")
    val message: String,

    @field:Schema(example = "user-123")
    val sessionId: String?
)

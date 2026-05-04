package ai.ai.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


data class OpenRouterResponse(
    val choices: List<Choice>
)


data class Choice(
    val message: Message
)


data class Message(
    val role: String,
    val content: String
)

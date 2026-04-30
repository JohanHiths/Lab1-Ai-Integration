package ai.ai.controller


import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chat")
class ChatController {

    val logger = LoggerFactory.getLogger(ChatController::class.java)



    @PostMapping
    @Operation(summary = "Send message to AI model")
    fun chat(
        @RequestBody request: ChatRequest
    ): ChatResponse {
        return ChatResponse(
            reply = "Test response"

        )

    }






}
package ai.ai.controller


import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chat")
class ChatController {

    val logger = LoggerFactory.getLogger(ChatController::class.java)



    @PostMapping
    fun chat(
        @RequestBody request: ChatRequest
    ): ChatResponse {
        return ChatResponse(
            reply = "Test response",
            response = TODO(),
            sessionID = TODO()
        )

    }

    @GetMapping
    fun chat(): ChatRequest {
        return ChatRequest(
            message = "Test message",
            sessionID = null

        )
    }






}
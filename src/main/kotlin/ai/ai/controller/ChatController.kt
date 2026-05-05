package ai.ai.controller


import ai.ai.client.ChatClient
import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
import ai.ai.service.ChatService

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chat")

class ChatController(private val chatService: ChatService
, private val chatClient: ChatClient
) {
         val localDateTime = java.time.LocalDateTime.now()
    val logger = LoggerFactory.getLogger(ChatController::class.java)



    @PostMapping
    @Operation(summary = "Send message to AI")
    @ApiResponse(responseCode = "200", description = "Successful response")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    fun chat(@Valid @RequestBody request: ChatRequest): ChatResponse {

        logger.info(
            "Received chat request: personality={}, messageLength={}",
            request.personality,
            request.message.length
        )


        val reply = chatService.sendMessage(request.message, request.personality)


        return ChatResponse(reply)


    }













}
package ai.ai.controller


import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
import ai.ai.service.ChatService
import ai.ai.service.OpenRouterTestService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chat")

class ChatController(private val chatService: ChatService
, private val openRouterTestService: OpenRouterTestService
) {

         val localDateTime = java.time.LocalDateTime.now()
    val logger = LoggerFactory.getLogger(ChatController::class.java)



    @PostMapping
    @Operation(summary = "Send message to AI")
    @ApiResponse(responseCode = "200", description = "Successful response")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    fun chat(@RequestBody request: ChatRequest): ChatResponse {

        logger.info("Received request: $request")

        val reply = chatService.sendMessage(request.message)

        return ChatResponse(reply)




    }


    @GetMapping("/test-openrouter")
    fun testOpenRouter(): String {
        return openRouterTestService.testCall()
    }







}
package ai.ai.controller


import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
import ai.ai.service.ChatService
import ai.ai.service.OpenRouterTestService
import io.swagger.v3.oas.annotations.Operation
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

    val logger = LoggerFactory.getLogger(ChatController::class.java)



    @PostMapping
    @Operation(method = "Send message to AI")
    fun chat(@RequestBody request: ChatRequest): ChatResponse {

        val reply = chatService.sendMessage(request.message)

        return ChatResponse(reply)
    }


    @GetMapping("/test-openrouter")
    fun testOpenRouter(): String {
        return openRouterTestService.testCall()
    }







}
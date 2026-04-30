package ai.ai.controller


import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
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
class ChatController {

    val logger = LoggerFactory.getLogger(ChatController::class.java)
    private val openRouterTestService: OpenRouterTestService
        get() {
            throw UnsupportedOperationException("Not supported yet.")
        }


    @PostMapping
    @Operation(summary = "Send message to AI model")
    fun chat(
        @RequestBody request: ChatRequest
    ): ChatResponse {
        return ChatResponse(
            reply =
                "Hello ${request.personality}!"
            + "\n\n"
            + request.message

        )

    }

    @GetMapping("/test-openrouter")
    fun testOpenRouter(): String {
        return openRouterTestService.testCall()
    }









}
package ai.ai.service

import ai.ai.dto.ChatResponse
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TestService {

    private val openRouterTestService: OpenRouterTestService = TODO("initialize me")

    @Value("\${llm.api-key}")
    lateinit var apiKey: String

    @PostConstruct
    fun init() {
        println("API KEY LOADED: ${apiKey.isNotBlank()}")
    }


    fun testCall(): ChatResponse {
        val reply = openRouterTestService.testCall()
        return ChatResponse(reply)
    }
}
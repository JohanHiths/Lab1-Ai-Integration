package ai.ai.service

import ai.ai.client.ChatClient
import ai.ai.dto.ChatRequest
import ai.ai.dto.ChatResponse
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class TestService(private val chatService: ChatService, private val chatClient: ChatClient) {


    @Value("\${llm.api-key}")
    lateinit var apiKey: String

    @PostConstruct
    fun init() {
        println("API KEY LOADED: ${apiKey.isNotBlank()}")
    }


   
    fun chat(request: ChatRequest): ChatResponse {
        val reply = chatService.sendMessage(request.message, request.personality)
        return ChatResponse(reply)
    }



}
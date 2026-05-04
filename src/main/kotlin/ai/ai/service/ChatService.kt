package ai.ai.service

import ai.ai.client.ChatClient
import org.springframework.stereotype.Service


@Service
class ChatService(
    private val chatClient: ChatClient
) {

    fun sendMessage(message: String, personality: String): String {
        val systemPrompt = buildSystemPrompt(personality)
        return chatClient.callLLM(message, systemPrompt)
    }





    fun buildSystemPrompt(personality: String): String {
        return when (personality.lowercase()) {
            "coder" -> "You are a helpful coding assistant."
            "pirate" -> "You speak like a pirate."
            else -> "You are a helpful assistant."
        }
    }



}
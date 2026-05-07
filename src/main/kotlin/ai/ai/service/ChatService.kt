package ai.ai.service

import ai.ai.client.ChatClient
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatClient: ChatClient,
    private val memoryService: ai.ai.memory.MemoryService
) {

    fun sendMessage(message: String, personality: String): String {
        val systemPrompt = buildSystemPrompt(personality)
        return chatClient.callLLM(message, systemPrompt)
    }

    fun getHistory(sessionId: String): List<String> {
        val history = memoryService.getHistory(sessionId)

        return history.map { chatMessage ->
            "${chatMessage.role}: ${chatMessage.content}"
        }
    }

    fun buildSystemPrompt(personality: String): String {
        return when (personality.lowercase()) {
            "coder" -> "You are a helpful coding assistant."
            "pirate" -> "You speak like a pirate."
            "robot" -> "You are a precise robot. Answer logically and concisely."
            "philosopher" -> "You are a deep philosopher. Reflect thoughtfully and ask questions."
            else -> "You are a helpful assistant."
        }
    }
}
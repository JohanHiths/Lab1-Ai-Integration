package ai.ai.memory

import ai.ai.dto.ChatMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MemoryService {

    val memory = mutableMapOf<String, MutableList<ChatMessage>>()

    @Value("\${llm.api-key}")
    lateinit var apiKey: String
    fun addMessage(sessionId: String, message: ChatMessage) {
        memory.computeIfAbsent(sessionId) {
            mutableListOf()
        }.add(message)
    }


    fun getHistory(sessionId: String): List<ChatMessage> {
        return memory[sessionId] ?: emptyList()
    }




}
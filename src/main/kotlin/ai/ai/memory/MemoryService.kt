package ai.ai.memory

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MemoryService {

    private val memory = mutableMapOf<String, MutableList<String>>()

    @Value("\${llm.api-key}")
    lateinit var apiKey: String
    fun addMessage(sessionId: String, message: String) {
        memory.computeIfAbsent(sessionId) {
            mutableListOf()
        }.add(message)
    }


    fun getHistory(sessionId: String): List<String> {
        return memory[sessionId] ?: emptyList()
    }
}
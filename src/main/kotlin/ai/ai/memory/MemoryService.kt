package ai.ai.memory

import ai.ai.dto.ChatMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

@Service
class MemoryService {

    private val memory = ConcurrentHashMap<String, MutableList<ChatMessage>>()


    fun addMessage(sessionId: String, message: ChatMessage) {
        val list = memory.computeIfAbsent(sessionId) {
            Collections.synchronizedList(mutableListOf())
        }
        list.add(message)
    }


    fun getHistory(sessionId: String): List<ChatMessage> {
        val list = memory[sessionId] ?: return emptyList()
        synchronized(list) { return list.toList() }
    }




}
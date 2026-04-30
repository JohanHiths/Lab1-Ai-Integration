package ai.ai.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
class ChatService {


    @Value("\${llm.api-key}")
    lateinit var apiKey: String
    fun getReply(message: String): String {
        return "Hello $message"
    }
    fun getReply(message: String, personality: String): String {
        return "Hello $message, I am $personality"
    }
}
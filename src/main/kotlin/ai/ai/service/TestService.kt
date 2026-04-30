package ai.ai.service

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TestService {

    @Value("\${llm.api-key}")
    lateinit var apiKey: String

    @PostConstruct
    fun init() {
        println("API KEY LOADED: ${apiKey.isNotBlank()}")
    }

}
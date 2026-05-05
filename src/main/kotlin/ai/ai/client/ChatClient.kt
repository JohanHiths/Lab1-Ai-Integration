package ai.ai.client

import ai.ai.dto.OpenRouterResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient



@Service
class ChatClient(


    private val restClient: RestClient,
    @Value("\${llm.base-url}") private val baseUrl: String,
    @Value("\${llm.api-key:}") private val apiKey: String,
    @Value("\${llm.provider}") private val provider: String

) {

    init{
        if (provider == "openrouter") {
            require(apiKey.isNotBlank()) { "llm.api-key must be set for provider openrouter" }
        }
    }


    fun callLLM(message: String, systemPrompt: String): String {

        println(">>> INSIDE ChatClient.callLLM <<<")

        val requestBody = mapOf(
            "model" to if (provider == "lmstudio") "local-model" else "openai/gpt-3.5-turbo",
            "messages" to listOf(
                mapOf("role" to "system", "content" to systemPrompt),
                mapOf("role" to "user", "content" to message)
            )
        )

        println(">>> SENDING REQUEST <<<")
        println(requestBody)

        val response = restClient.post()
            .uri("$baseUrl/v1/chat/completions")
            .headers {
                it.set("Content-Type", "application/json")
                if (provider == "openrouter") {
                    require(apiKey.isNotBlank()) { "API key missing for OpenRouter" }
                    it.set("Authorization", "Bearer $apiKey")
                }
            }
            .body(requestBody)
            .retrieve()
            .body(OpenRouterResponse::class.java)

        println(">>> PARSED RESPONSE <<<")
        println(response)

        return response?.choices
            ?.firstOrNull()
            ?.message
            ?.content
            ?: "No response"
    }
}





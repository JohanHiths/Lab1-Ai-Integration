package ai.ai.client

import ai.ai.dto.OpenRouterResponse
import ai.ai.exception.ServiceUnavailableException
import org.springdoc.core.service.RequestBodyService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient




@Service
class ChatClient(


    private val restClient: RestClient,
    @Value("\${llm.base-url}") private val baseUrl: String,
    @Value("\${llm.api-key:}") private val apiKey: String,
    @Value("\${llm.provider}") private val provider: String,
    private val requestBody: RequestBodyService,
    private val requestBodyService: RequestBodyService

) {

    init {
        if (provider == "openrouter") {
            require(apiKey.isNotBlank()) { "llm.api-key must be set for provider openrouter" }
        }
    }


    fun callLLM(message: String, systemPrompt: String): String {

        val maxRetries = 3
        var attempt = 0
        var delayMs = 500L

        val requestBody = mapOf(
            "model" to if (provider == "lmstudio") "local-model" else "openai/gpt-3.5-turbo",
            "messages" to listOf(
                mapOf("role" to "system", "content" to systemPrompt),
                mapOf("role" to "user", "content" to message)
            )
        )



        while (true) {
            try {
                val response = restClient.post()
                    .uri("$baseUrl/v1/chat/completions")
                    .headers {
                        it.set("Content-Type", "application/json")
                        if (provider == "openrouter") {
                            it.set("Authorization", "Bearer $apiKey")
                        }
                    }
                    .body(requestBody)
                    .retrieve()
                    .body(OpenRouterResponse::class.java)

                return response?.choices
                    ?.firstOrNull()
                    ?.message
                    ?.content
                    ?.trim()
                    ?: "No response"

            } catch (ex: Exception) {

                val shouldRetry =
                    ex is org.springframework.web.client.HttpServerErrorException ||
                            ex is org.springframework.web.client.HttpClientErrorException.TooManyRequests

                if (!shouldRetry || attempt >= maxRetries) {
                    throw ServiceUnavailableException("LLM failed after retries")
                }

                println("Retry attempt ${attempt + 1} after ${delayMs}ms")

                Thread.sleep(delayMs)
                delayMs *= 2
                attempt++
            }
        }
    }
}





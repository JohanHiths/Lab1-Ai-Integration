package ai.ai.service


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class OpenRouterTestService(

    private val webClient: WebClient,
    @Value("\${llm.api-key}") private val apiKey: String
) {

    fun testCall(): String {

        val response = webClient.post()
            .uri("https://openrouter.ai/api/v1/chat/completions")
            .header("Authorization", "Bearer $apiKey")
            .header("Content-Type", "application/json")
            .header("HTTP-Referer", "http://localhost:8080")
            .header("X-Title", "My Spring App")



            .bodyValue(
                mapOf(
                    "model" to "openai/gpt-3.5-turbo",
                    "messages" to listOf(
                        mapOf(
                            "role" to "user",
                            "content" to "Say hello in a fun way"
                        )
                    )
                )
            )
            .retrieve()
            .bodyToMono(String::class.java)
            .block()




        return response ?: "No response"

    }
}
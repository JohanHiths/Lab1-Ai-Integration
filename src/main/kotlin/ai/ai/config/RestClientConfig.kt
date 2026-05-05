package ai.ai.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class RestClientConfig {

    @Bean
    fun restClient(builder: RestClient.Builder): RestClient {
        println(">>> CALLING LLM <<<")
        return builder.build()

    }

}
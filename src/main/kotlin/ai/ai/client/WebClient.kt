package ai.ai.client

import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient





class WebClient {


    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {

        return builder
            .baseUrl("http://localhost:1234")

            .build()

    }

}
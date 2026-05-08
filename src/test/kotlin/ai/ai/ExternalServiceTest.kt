package ai.ai

import ai.ai.client.ChatClient
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.stubbing.Scenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.wiremock.spring.EnableWireMock
import kotlin.test.Test


@SpringBootTest(
    properties = [
        "llm.provider=openrouter",
        "llm.api-key=test-key",
        "llm.base-url=http://localhost:8080"
    ]
)
@EnableWireMock
class ExternalServiceTest {


    @Autowired
    lateinit var chatClient: ChatClient

    @Test
    fun `should call OpenRouter API`() {
        stubFor(
            post(urlEqualTo("/v1/chat/completions"))
                .inScenario("Retry")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(aResponse().withStatus(429))
                .willSetStateTo("second")
        )

        stubFor(
            post(urlEqualTo("/v1/chat/completions"))
                .inScenario("Retry")
                .whenScenarioStateIs("second")
                .willReturn(aResponse().withStatus(503))
                .willSetStateTo("success")
        )

        stubFor(
            post(urlEqualTo("/v1/chat/completions"))
                .inScenario("Retry")
                .whenScenarioStateIs("success")
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(
                            """
            {
              "choices": [
                {
                  "message": {
                    "content": "Hello from WireMock"
                  }
                }
              ]
            }
            """.trimIndent()
                        )
                )
        )
    }
}

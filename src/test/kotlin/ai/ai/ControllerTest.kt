package ai.ai




import ai.ai.controller.ChatController
import ai.ai.service.ChatService
import ai.ai.service.OpenRouterTestService
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest






@WebMvcTest(controllers = [ChatController::class])

class ControllerTest {

    @MockitoBean
    lateinit var chatService: ChatService

    @MockitoBean
    lateinit var openRouterTestService: OpenRouterTestService



    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `should return AI response`() {
        whenever(chatService.sendMessage(any()))
            .thenReturn("Test response")

        val json = """
            {
              "personality": "coder",
              "message": "Hello",
              "sessionId": "user-123"
            }
        """.trimIndent()

        mockMvc.perform(
            post("/api/v1/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.reply").value("Test response"))
    }


    @Test
    fun `should return 500 when service fails`() {

        whenever(chatService.sendMessage(any()))
            .thenThrow(RuntimeException("Service unavailable"))

        val json = """
        {
          "personality": "coder",
          "message": "Hello",
          "sessionId": "user-123"
        }
    """

        mockMvc.perform(
            post("/api/v1/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
            .andExpect(status().isInternalServerError)
            .andExpect(jsonPath("$.message").value("Something went wrong"))
    }

    @Test
    fun `should return 400 when request is invalid`() {

        val invalidJson = """
        {
          "personality": "",
          "message": "",
          "sessionId": "user-123"
        }
    """.trimIndent()

        mockMvc.perform(
            post("/api/v1/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson)
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.message").exists())
    }


}

package ai.ai

import ai.ai.service.ChatService
import ai.ai.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test


@SpringBootTest
class ServiceTest {

    @Autowired
    lateinit var chatService: ChatService

    @Autowired
    lateinit var testService: TestService



    @Test
    fun testChatisNotEmpty() {




    }
}
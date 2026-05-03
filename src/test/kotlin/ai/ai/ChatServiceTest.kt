package ai.ai


import ai.ai.service.ChatService
import ai.ai.service.OpenRouterTestService
import ai.ai.service.TestService

import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.test.context.ActiveProfiles

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest

import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
@ActiveProfiles("test")
class ChatServiceTest() {

    @Mock
    lateinit var openRouterTestService: OpenRouterTestService


    @InjectMocks
    lateinit var chatService: ChatService

    @InjectMocks
    lateinit var TestService: TestService



    @Test
    fun `should return response from openRouter`() {

        whenever(openRouterTestService.testCall())
            .thenReturn("Test response")

        val result = TestService.testCall()

        assertEquals("Test response", result.reply)
    }


}